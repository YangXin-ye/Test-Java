import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store';
// 1. 引入实例
// 如果在 utils 文件夹下只创建了一个 index.js 文件，那么 webpack 会默认把该文件打包，所以路径可以不用加上 '/index.js' 
// 如果你创建的文件名不是 index，那么一定要加上文件名
// 如果你的 axios 配置文件跟我创建的路径不一样，那么按照你的路径引入就可以了
import axios from './axios';

Vue.config.productionTip = false
Vue.use(ElementUI);
// 2. 与Vue实例关联
Vue.prototype.$axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
