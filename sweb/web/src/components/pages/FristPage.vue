<template>
    <div>
        <el-row>
            <el-col :span="8">
                <el-card class="box-card">
                    <div class="user">
                            <img :src="avatarUrl" alt="">
                            <div class="userinfo">
                                <p class="name">{{ name }}</p>
                                <p class="access">{{access}}</p>
                            </div> 
                    </div>
                    <div class="login-info">
                        <p>上次登录时间：<span>{{ time }}</span></p>
                        <p>上次登录地点：<span>{{ address }}</span></p>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import {getUserInfo} from '@/api/api' 
export default {
    name: "fristpage",
    data() {
        return {
            name: "",
            access: "",
            time: "",
            address: "",
            avatarUrl: 'https://yxy-live-share.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20240419210622.jpg'
        }
    },
    mounted() {
        this.getInfo()
    },
    methods: {
        getInfo() {
            getUserInfo().then(res=>{
                console.log(res.data.username);
                this.name = res.data.username
                this.access = res.data.role
                this.time = res.data.time
                this.address = res.data.address
                this.avatar = res.data.avatar
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