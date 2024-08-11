<template>
  <div>
    <el-card>
      <el-button type="primary" @click="getData2(1)">学生数据</el-button>
      <el-button type="primary" @click="getData2(2)">员工数据</el-button>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          v-for="column in columns"
          :key="column.prop"
          :label="column.label"
          :prop="column.prop">
        </el-table-column>
      </el-table>
      <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
                     :current-page="pageIndex" :page-sizes="[5, 10, 20, 50, 100]" :page-size="pageSize" :total="total"
                     layout="total, sizes, prev, pager, next, jumper" style="margin-top:30px;">
      </el-pagination>

    </el-card>
  </div>
</template>

<script>
import { dynamicTable } from '@/api/dynamicTable'

export default {
  name: 'DynamicTable',
  data() {
    return {
      columns: [], // 存储表格的列信息
      tableData: [], // 存储表格的数据
      pageIndex: 1,
      pageSize: 5,
      total: 0,
      type:1

    }
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData(){
      const params = {
        pageNum: this.pageIndex,
        pageSize: this.pageSize,
        type:this.type,
      }
      dynamicTable({params}).then(res=>{
        console.log(res)
        this.columns = res.data.columns;
        this.tableData=res.data.data;
        // 获取总数据
        this.total = res.data.total
      })
    },
    getData2(type){
      this.type = type;
      this.pageIndex = 1;
      this.getData();
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
  },
}
</script>


<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border: 1px dashed #8c939d;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
