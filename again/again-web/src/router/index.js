import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/common/Home'
import CustomerList from '../components/pages/customer/CustomerList.vue'
import CommonCustomer from '../components/pages/customer/CommonCustomer.vue'
import AllOrder from '../components/pages/order/AllOrder.vue'
import MyOrder from '../components/pages/order/MyOrder.vue'
import FristPage from '../components/pages/FristPage.vue'
import Login from '../components/pages/loginAndRegister/Login.vue'
import AllData from '../components/pages/data/AllData.vue'
import Echarts from '../components/pages/echarts/Echarts.vue'
import Product from '../components/pages/product/Product.vue'
import Categories from '../components/pages/product/Categories.vue'
import EsData from '../components/pages/esData/EsData.vue'
import UpInfo from '../components/pages/upInfo/upInfo.vue'
import MenuList from '../components/pages/menu/MenuList.vue'
import SqlOut from '../components/pages/sqlOpt/SqlOut.vue'
import DynamicTable from '../components/pages/dynamicTable/DynamicTable.vue'



Vue.use(Router)

export default new Router({
  routes: [
    {
      // 修改这里的路由规则
      path: '/',
      name: 'Home',
      redirect:'FristPage',
      component: Home,
      // 在这书写子路由规则，这样页面就可以嵌套进主路由当中
      children: [
        {
          path: '/FristPage',
          name: 'FristPage',
          component: FristPage
        },
        {
          path: '/CustomerList',
          name: 'customerList',
          component: CustomerList
        },
        {
          path: '/CommonCustomer',
          name: 'commonCustomer',
          component: CommonCustomer
        },
        {
          path: '/AllOrder',
          name: 'allOrder',
          component: AllOrder
        },
        {
          path: '/MyOrder',
          name: 'myOrder',
          component: MyOrder
        },
        {
          path: '/AllData',
          name: 'allData',
          component: AllData
        },
        {
          path: '/Echarts',
          name: 'Echarts',
          component: Echarts
        },
        {
          path: '/Product',
          name: 'Product',
          component: Product
        },
        {
          path: '/Categories',
          name: 'Categories',
          component: Categories
        },
        {
          path: '/EsData',
          name: 'Categories',
          component: EsData
        },
        {
          path: '/upInfo',
          name: 'upInfo',
          component: UpInfo
        },
        {
          path: '/menuList',
          name: 'menuList',
          component: MenuList
        },
        {
          path: '/menuList',
          name: 'menuList',
          component: MenuList
        },
        {
          path: '/sqlOptimize',
          name: 'sqlOpt',
          component: SqlOut
        },
        {
          path: '/dynamicTable',
          name: 'sqlOpt',
          component: DynamicTable
        }
      ]
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }
  ]
})
