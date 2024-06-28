import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
// 引入页面
import Login from '@/components/common/Login.vue'
import CutomerList from '@/components/pages/customer/CutomerList'
import AllOrder from '@/components/pages/order/AllOrder'
import FristPage from '@/components/pages/FristPage.vue'
import AllData from '@/components/pages/data/AllData.vue'
import Echarts from '@/components/pages/echarts/Echarts.vue'
import Father from '@/components/pages/command/Father.vue'
import Son from '@/components/pages/command/Son.vue'
import Product from '@/components/pages/product/Product.vue'
import Categories from '@/components/pages/product/Categories.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      redirect: 'FristPage',
      children: [
        {
          path: '/FristPage',
          name: 'fristpage',
          component: FristPage
        },
        {
          path: '/CutomerList',
          name: 'CutomerList',
          component: CutomerList,
        },
        {
          path: '/AllOrder',
          name: 'AllOrder',
          component: AllOrder,
        },
        {
          path: '/AllData',
          name: 'AllData',
          component: AllData
        },
        {
          path: '/Echarts',
          name: 'Echarts',
          component: Echarts
        },
        {
          path: '/Father',
          name: 'Father',
          component: Father
        },
        {
          path: '/Son',
          name: 'Son',
          component: Son
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
