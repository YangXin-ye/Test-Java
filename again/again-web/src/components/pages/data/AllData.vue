<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="搜索信息">
        <el-input placeholder="请输入..."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getData()">查询</el-button>
        <el-button type="primary" @click="openDialog()">新增</el-button>
        <template>
          <el-button type="warning" @click="open">注意</el-button>
        </template>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border style="width: 100%">
      <el-table-column prop="id" label="编号" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column label="操作" width="180" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="openDialogUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" @click="deleteUser(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                   :page-sizes="[3, 5, 10, 20, 50, 100]" :page-size="pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" style="margin-top:30px;">
    </el-pagination>

    <!-- 弹框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" :model="form">
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <div class="block">
            <el-cascader ref="cascader" v-model="list" :options="options" :props="{ checkStrictly: true }"></el-cascader>
          </div>
        </el-form-item>
        <el-button type="success" @click="saveOrUpdate()">{{ title }}</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getData, insertData, updateData, deleteData, getDataProvinceAndCityAndArea } from '@/api/data'

export default {
  name: 'AllData',
  data() {
    return {
      dataList: [],
      pageIndex: 1,
      pageSize: 3,
      total: 0,
      title: '新增',
      dialogFormVisible: false,
      form: {
        id: '',
        name: '',
        phone: '',
        address: ''
      },
      options: []
    }
  },
  mounted() {
    this.getData()
    this.getTree()
  },
  methods: {
    getData() {
      const params = {
        pageNum: this.pageIndex,
        pageSize: this.pageSize
      }
      getData({params}).then(res => {
        this.dataList = res.data.records
        // 获取总数据
        this.total = res.data.total
      })
    },
    openDialog() {
      this.dialogFormVisible = true
    },
    saveOrUpdate() {
      if (this.form.id) {
        this.update()
      } else {
        this.insert()
      }
    },
    insert() {
      // 新增
      insertData(this.form).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '添加成功',
            type: 'success'
          })
          this.getData()
          this.dialogFormVisible = false
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }

      })
    },
    openDialogUpdate(dataList) {
      // 打开窗口
      this.dialogFormVisible = true;
      this.title = '修改用户'
      this.form = {
        ...dataList
      }
    },
    update() {
      let arr = this.$refs.cascader.getCheckedNodes()
      let SHENG = ''
      let SHI = ''
      let QU = ''
      for (let i = 0; i < arr.length; i++) {
        QU = arr[i].label
        if (arr[i].parent) {
          SHI = arr[i].parent.label
          if (arr[i].parent.parent) {
            SHENG = arr[i].parent.parent.label
          }
        }
      }
      let address = SHENG + SHI + QU
      this.form.address = address

      const data = {
        ...this.form
      }
      // 修改
      updateData(data).then(res => {
        if (res.code === 200) {
          this.listData = this.form.address
          this.$message({
            message: '修改成功',
            type: 'success'
          })
          this.getData()
          this.dialogFormVisible = false
        } else {
          this.$message({
            message: '修改失败',
            type: 'error'
          })
        }
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.getData();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getData();
    },
    // 删除用户
    deleteUser(id) {
      deleteData(id).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getData()
        } else {
          this.$message({
            message: '删除失败',
            type: 'error'
          })
        }
      })
    },
    open() {
      this.$alert('地区的选择可以按需选择', '说明', {});
    },
    getTree() {
      const level = {
          level: 'province'
        }
        getDataProvinceAndCityAndArea({ params: level }).then(res => {
        // console.log(res);
        this.options = res.data
        // console.log(this.options);

        this.options.forEach(item => {
          item.value = item.id
          item.label = item.name

          const levelCity = {
            districtId: item.id,
            level: 'province'
          }
          getDataProvinceAndCityAndArea({ params: levelCity }).then(res => {
            // console.log(res);
            item.children = res.data
            item.children.forEach(item => {
              item.value = item.id
              item.label = item.name
              const levelArea = {
                districtId: item.id,
                level: 'city'
              }
              getDataProvinceAndCityAndArea({ params: levelArea }).then(res => {
                // console.log(res);
                item.children = res.data
                item.children.forEach(item => {
                  item.value = item.id
                  item.label = item.name
                })
                // console.log(item.children);
              })
            })
          })
        })
      })
    },
  }
}
</script>
