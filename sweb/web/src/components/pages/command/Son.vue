<template>
    <div>
        <h2>子组件</h2>
        <p>{{ message }}</p>
        <button @click="sendMessage">传递给父组件</button>
        <div>数据：{{ value }}</div>
    </div>
</template>

<script>
export default {
    name: 'Son',
    props: {
        message: String
    },
    data() {
        return {}
    },
    methods: {
        sendMessage() {
            this.$emit('childMessage', '这是子组件的数据');
        },
    },
    computed: {
        value() {
            return this.$store.state.set.value;
        }
    }
}
</script>