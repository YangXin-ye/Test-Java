<template>
  <div>
    <el-card>

      <el-form :inline="true" class="demo-form-inline">
        <el-button type="primary" @click="list1()">limit优化前查询</el-button>
        <el-button type="primary" @click="list2()">limit优化后查询</el-button>
        <el-button type="primary" @click="list3()">单个索引优化前查询</el-button>
        <el-button type="primary" @click="list3two()">单个索引优化后查询</el-button>
        <el-button type="primary" @click="list4()">复合索引优化前查询</el-button>
        <el-button type="primary" @click="list4two()">复合索引优化后查询</el-button>
        <el-button type="primary" @click="list5()">函数优化前查询</el-button>
        <el-button type="primary" @click="list6()">函数优化后查询</el-button>
        <el-button type="primary" @click="list7()">避免回表优化前查询</el-button>
        <el-button type="primary" @click="list7two()">避免回表优化后查询</el-button>
      </el-form>

      <h1>
        limit优化前查询速度：{{limit1}}
      </h1>
      <h1>
        limit优化后查询速度：{{limit2}}
      </h1>
      <h1>
        单个索引优化前查询速度：{{one1}}
      </h1>
      <h1>
        单个索引优化后查询速度：{{one2}}
      </h1>
      <h1>
        复合索引优化前查询速度：{{two1}}
      </h1>
      <h1>
        复合索引优化后查询速度：{{two2}}
      </h1>
      <h1>
        函数优化前查询速度：{{funBefore}}
      </h1>
      <h1>
        函数优化后查询速度：{{funAft}}
      </h1>
      <h1>
        避免回表优化前查询速度：{{huiBiao}}
      </h1>
      <h1>
        避免回表优化后查询速度：{{huiBiao2}}
      </h1>


    </el-card>
  </div>
</template>

<script>
import { list1,list2,list3,list4,list5,list6,list7 } from '@/api/optSql'

export default {
  name: 'SqlOut',
  data() {
    return {
      limit1:'',
      limit2:'',
      one1:'',
      one2:'',
      two1:'',
      two2:'',
      funBefore:'',
      funAft:'',
      huiBiao:'',
      huiBiao2:''
    }
  },
  mounted() {

  },
  methods: {
      list1(){
        list1().then(res=>{
          console.log(res);
          this.limit1=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
        })
      },
    list2(){
      list2().then(res=>{
        console.log(res);
        this.limit2=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list3(){
      list3().then(res=>{
        console.log(res);
        this.one1=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list3two(){
      list3().then(res=>{
        console.log(res);
        this.one2=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list4(){
      list4().then(res=>{
        console.log(res);
        this.two1=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list4two(){
      list4().then(res=>{
        console.log(res);
        this.two2=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list5(){
      list5().then(res=>{
        console.log(res);
        this.funBefore=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list6(){
      list6().then(res=>{
        console.log(res);
        this.funAft=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list7(){
      list7().then(res=>{
        console.log(res);
        this.huiBiao=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
    },
    list7two(){
      list7().then(res=>{
        console.log(res);
        this.huiBiao2=res.data +"毫秒，"+parseFloat(res.data)/1000+"秒";
      })
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
