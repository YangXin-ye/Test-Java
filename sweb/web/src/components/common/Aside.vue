<template>
    <div class="asidecontainer">
        <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
            :collapse="isCollapse">
            <h3>管理系统</h3>
            <el-menu-item index="2" 
            v-for="item in noChildren" 
            :key="item.name" 
            :index="item.name" 
            @click="clickRoute(item)">
                <i :class="`el-icon-${item.icon}`"></i>
                <span slot="title">{{ item.label }}</span>
            </el-menu-item>
            <el-submenu index="1" v-for="item in hasChildren" :key="item.name" :index="item.name">
                <template slot="title">
                    <i :class="`el-icon-${item.icon}`"></i>
                    <span slot="title">{{ item.label }}</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="1-1" 
                    v-for="subitem in item.childred" 
                    :key="subitem.name" 
                    :index="subitem.name" 
                    @click="clickRoute(subitem)">
                    {{ subitem.label }}
                    </el-menu-item>
                </el-menu-item-group>
            </el-submenu>

        </el-menu>
    </div>
</template>

<script>
export default {
    name: 'Aside',
    data() {
        return {
            menu: [
                {
                    path: '/',
                    name: 'main',
                    label: '首页',
                    icon: 's-home',
                    url: 'Home/Home'
                },
                {
                    label: 'Vue基础',
                    icon: 'tickets',
                    name: 'command',
                    childred: [
                        {
                            path:'/Father',
                            name: 'Command',
                            label: '父页面',
                            url: 'command/Father'
                        },
                        {
                            path:'/Son',
                            name: 'Son',
                            label: '子页面',
                            url: 'command/Son'
                        }
                    ]
                },
                {
                    label: '用户管理',
                    icon: 'user',
                    name: 'customer',
                    childred: [
                        {
                            path:'/CutomerList',
                            name: 'CustomerList',
                            label: '用户列表',
                            url: 'customer/CutomerList'
                        }
                    ]
                },
                {
                    label: '商品管理',
                    icon: 's-data',
                    name: 'product',
                    childred: [
                        {
                            path:'/Product',
                            name: 'Product',
                            label: '商品列表',
                            url: 'product/Product'
                        },
                        {
                            path:'/Categories',
                            name: 'Categories',
                            label: '分类列表',
                            url: 'product/Categories'
                        }
                    ]
                },
                {
                    label: '数据管理',
                    icon: 's-fold',
                    name: 'data',
                    children: [
                        {
                            path:'/AllData',
                            name: 'AllData',
                            label: '数据处理',
                            url: 'data/AllData'
                        }
                    ]
                },
                {
                    label: 'Echarts图表',
                    icon: 's-opportunity',
                    name: 'echarts',
                    childred: [
                        {
                            path:'/Echarts',
                            name: 'Echarts',
                            label: 'Echarts图表',
                            url: 'echarts/Echarts'
                        }
                    ]
                },
                {
                    label: '订单管理',
                    icon: 's-order',
                    name: 'order',
                    childred: [
                        {
                            path:'/AllOrder',
                            name: 'AllOrder',
                            label: '全部订单',
                            url: 'order/AllOrder'
                        }
                    ]
                }
            ],
            isCollapse:null
        };
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        clickRoute(item) {
            if(this.$route.path !== item.path && !(this.$route.path === '/FristPage' && item.path === '/')) {
            this.$router.push(item.path)
            }
        }
    },
    computed: {
        noChildren() {
            return this.menu.filter(item => !item.childred)
        },
        hasChildren() {
            return this.menu.filter(item => item.childred)
        }
    }
}
</script>

<style scoped lang="less">
.el-menu {
    height: 100vh;
    background-color: rgb(100, 214, 237);
    border-right: none;

    h3 {
        color: #fff;
        text-align: center;
        line-height: 48px;
        font-size: 16px;
        font-weight: 400;
    }
}
</style>