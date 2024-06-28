<template>
    <div class="header-container">
        <div class="l-context">
            <el-button plain icon="el-icon-menu" size="medium" @click="handleMenu"></el-button>
            <span class="fristpage">首页</span>
        </div>
        <div class="r-context">
            <el-dropdown>
                <span class="el-dropdown-link">
                    <img src="../../assets/images/user.png" alt="" class="user_img">
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人信息</el-dropdown-item>
                    <el-dropdown-item>注销</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Header',
    data() {
        return {}
    },
    methods: {
        handleMenu() {
            this.$store.commit('collapseChange')
        }
    }
}
</script>

<style scoped>
.header-container {
    background-color: cadetblue;
    height: 70px;
    color: #fff;
    padding: 0 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .r-context {
        padding-right: 20px;

        .user_img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
        }
    }

    .fristpage {
        color: #fff;
        font-size: 16px;
        margin-left: 10px;
    }
}
</style>