export default {
    state: {
        value: ''
    },
    mutations: {
        setContent(state, content) {
            state.value = content;
        }
    }
}