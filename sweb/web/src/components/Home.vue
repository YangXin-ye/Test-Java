<template>
  <div class="hello">
    <el-container>
      <el-aside width="auto">
          <Aside />
        </el-aside>
      <el-container>
        <el-header>
          <Header></Header>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>


  </div>
</template>

<script>
import Aside from './common/Aside'
import Header from './common/Header'

export default {
  name: 'Home',
  data() {
    return {

    }
  },
  components: {
    Aside,
    Header,
  }
}
</script>

<style scoped>
.el-header {
  padding: 0;
}
</style>