<template>
    <div>
        <h2 v-if="hasContent">有内容</h2>
        <h2 v-else-if="hasMoreContent">有更多内容</h2>
        <h2 v-else>没有更多内容了</h2>
        <ul>
            <li v-for="item in items" :key="item">
                <span v-text="item"></span>
            </li>
        </ul>
        <input v-model="text1" placeholder="输入内容" />
        <p>输入的内容是：{{ text1 }}</p>
        <input v-model="text2" placeholder="输入内容" />
        <p>输入的内容是：{{ text2 }}</p>
        <input type="text" v-model="text3" v-bind:placeholder="'输入更多内容：' + text2" />
        <button @click="handleClick" ref="myButton">点击我</button>
        <hr>
        <h1>下方是子组件的内容</h1>
        <Son :message="parentMessage"></Son>

        <Son @childMessage="handleChildMessage"></Son>
        <p>子组件传递过来的数据: {{ childMessage }}</p>

        <hr>
        <input type="text" v-model="info" @input="onInput" />
    </div>
</template>

<script>
import Son from "./Son.vue";

export default {
    name: 'Father',
    data() {
        return {
            text1: '默认内容',
            text2: '',
            text3: '',
            items: ['这是第一项', '这是第二项', '这是第三项'],
            hasContent: true,
            hasMoreContent: false,
            parentMessage: '这是来自父组件的消息',
            childMessage: '',
            info: ''
        }
    },
    methods: {
        handleClick() {
            console.log('按钮被点击了！');

            this.changeColorByDelay(['red', 'blue', 'green', ''], 1);
        },
        changeColorByDelay(colors, delayInSeconds) {
            const button = this.$refs.myButton;
            let delay = delayInSeconds * 1000;

            for (let color of colors) {
                setTimeout(() => {
                    button.style.backgroundColor = color;
                }, delay);
                delay += delayInSeconds * 1000;
            }
        },
        handleChildMessage(message) {
            this.childMessage = message;
        },
        onInput(event) {
            this.$store.commit('setContent', event.target.value);
        }
    },
    components: {
        Son
    },
    computed: {
        message() {
            return this.$store.state.state.set.info;
        }
    }
}
</script>