export default {
    state:{
        isCollapse: false
    },
    mutations: {
        collapseChange(state) {
            state.isCollapse = !state.isCollapse
        }
    }
}