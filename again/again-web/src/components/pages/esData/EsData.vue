<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="搜索信息">
        <el-input placeholder="请输入..." v-model="name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getData()">查询</el-button>
        <el-button type="primary" @click="syncEsData()">同步ES数据</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border style="width: 100%">
      <el-table-column prop="cityCode" label="城市编码" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="adcode" label="区域编码" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="name" label="行政区名称" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="center" label="区域中心点" width="180" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="level" label="行政区划级别" width="180" header-align="center" align="center">
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
                   :page-sizes="[3, 5, 10, 20, 50, 100]" :page-size="pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" style="margin-top:30px;">
    </el-pagination>
  </div>
</template>

<script>
import { getData,syncData  } from '@/api/esApi'

export default {
  name: 'EsData',
  data() {
    return {
      dataList: [],
      pageIndex: 1,
      pageSize: 3,
      total: 0,
      name:"",
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
      const params = {
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        name:this.name
      }
      getData({params}).then(res => {
        this.dataList = res.data.records
        // 获取总数据
        this.total = res.data.total
      })
    },
    syncEsData() {
      syncData().then(res => {
        console.log(res);
        this.$message({
          message: '同步成功',
          type: 'success'
        })
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
  }
}
</script>
