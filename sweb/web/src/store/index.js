import Vue from 'vue'
import Vuex from 'vuex'
import tab from './table'
import set from './set'

Vue.use(Vuex)
export default new Vuex.Store({
    modules:{
        tab,
        set
    }
})