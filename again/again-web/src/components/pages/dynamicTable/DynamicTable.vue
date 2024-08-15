<template>
  <div>
    <el-card>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input placeholder="请输入学生名称" type="text" v-model="searchKey"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getData()">查询</el-button>
          <el-button type="primary">新增</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="tableData" style="width: 100%">
        <el-table-column v-if="tableData.some(row => row.SID)" prop="SID" label="学号"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SNAME)" prop="SNAME" label="学生名称"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SAGE)" prop="SAGE" label="学生年龄"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SSEX)" prop="SSEX" label="学生性别"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SNATIVEPLACE)" prop="SNATIVEPLACE" label="学生地址"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SMAJOR)" prop="SMAJOR" label="学生专业"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SCLASS)" prop="SCLASS" label="学生班级"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SNATIVE)" prop="SNATIVE" label="学生族"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.CNAME)" prop="CNAME" label="考试科目"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.CVAL)" prop="CVAL" label="科目学分"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.CTIME)" prop="CTIME" label="课时"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.CMARK)" prop="CMARK" label="成绩"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.TID)" prop="TID" label="教师Id"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.TNAME)" prop="TNAME" label="教师名称"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.TSEX)" prop="TSEX" label="教师性别"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.TAGE)" prop="TAGE" label="教师年龄"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.TLVL)" prop="TLVL" label="教师级别"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.EMPNO)" prop="EMPNO" label="员工编号"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.ENAME)" prop="ENAME" label="员工姓名"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.JOB)" prop="JOB" label="员工职位"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.MGR)" prop="MGR" label="经理编号"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.HIREDATE)" prop="HIREDATE" label="入职日期"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.ESAL)" prop="ESAL" label="工资"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.ECOMM)" prop="ECOMM" label="佣金"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.DEPTNO)" prop="DEPTNO" label="部门编号"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.DNAME)" prop="DNAME" label="部门名称"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.LOC)" prop="LOC" label="部门位置"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.SAL)" prop="SAL" label="奖金金额"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.COMM)" prop="COMM" label="奖金佣金"></el-table-column>
        <el-table-column v-if="tableData.some(row => row.GRADE)" prop="GRADE" label="工资等级"></el-table-column>
        <el-table-column label="操作" width="180" header-align="center" align="center">
          <template slot-scope="scope">
            <el-button type="primary" round size="small">编辑</el-button>
            <el-button type="danger" round size="small">删除</el-button>
          </template>
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
      type:1,
      searchKey:'',

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
        searchKey:this.searchKey,
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
    }
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
