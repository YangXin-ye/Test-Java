import Vue from 'vue'
import Vuex from 'vuex'
import tab from './table'

Vue.use(Vuex)
export default new Vuex.Store({
  modules: {
    tab
  }
})
