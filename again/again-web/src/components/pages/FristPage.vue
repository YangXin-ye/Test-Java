<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-card class="box-card">
          <div class="user">
            <img :src="avatar"/>
            <div class="userinfo">
              <p class="name">{{ username }}</p>
              <p class="access">{{ access }}</p>
            </div>
          </div>
          <div class="login-info">
            <p>上次登录的时间:<span>2024-05-20</span></p>
            <p>上次登录的地点:<span>南京</span></p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">

      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getHomeData } from "../../api/user";

export default {
  name: "FristPage",
  data() {
    return {
      username: "",
      access: "",
      time: "",
      address: "",
      avatar:""
    }
  },
  mounted() {
    this.getInfo()
  },
  methods: {
    getInfo() {
      getHomeData().then(res => {
        this.username = res.data.username
        this.avatar = res.data.avatar;
        if (res.data.role === "ADMIN") {
          this.access = "管理员"
        }
      })
    }
  }
}
</script>

<style scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;

  img {
    margin-left: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }

  .userinfo {
    margin-left: 40px;

    .name {
      font-size: 32px;
      margin-bottom: 10px;

    }

    .access {
      color: #999;
    }
  }
}

.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;

    span {
      color: #666666;
      margin-left: 20px;
    }
  }
}
</style>
