# Vue基础

## 基础指令

```vue
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

    </div>
</template>

<script>
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
        }
    }
}
</script>
```

## ref和this

`@click` 事件处理函数 `handleClick`。当按钮被点击时，`handleClick` 方法会被调用，并在控制台打印一条消息。

同时，使用 `ref` 属性为按钮添加了一个引用，名为 `myButton`。在 `handleClick` 方法中，我们可以使用 `this.$refs.myButton` 来获取按钮元素，并对其进行操作，例如修改样式。

```vue
<template>
    <div>
      <button @click="handleClick" ref="myButton">点击我</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Son',
    data() {
      return {}
    },
    methods: {
      handleClick() {
        console.log('按钮被点击了！');
        // 使用 ref 获取按钮元素
        const button = this.$refs.myButton;
        // 对按钮进行操作，例如修改样式
        button.style.backgroundColor ='red';
      }
    }
  }
  </script>
  
```

延时的使用

```js
            // 延时一秒后恢复按钮的背景颜色
            setTimeout(() => {
                button.style.backgroundColor = 'blue';
            }, 1000);
```

可以嵌套使用

```js
            // 延时一秒后恢复按钮的背景颜色
            setTimeout(() => {
                button.style.backgroundColor = 'blue';
                setTimeout(() => {
                    button.style.backgroundColor = ' green';
                }, 1000);
                setTimeout(() => {
                    button.style.backgroundColor = '';
                }, 1000);
            }, 1000);
```

利用循环优化

```js
            // 定义数组
            let colors = ['blue', 'green', ''];
            let delay = 1000;

            // 使用 setTimeout 循环修改按钮的背景颜色
            for (let color of colors) {
                setTimeout(() => {
                    button.style.backgroundColor = color;
                }, delay);
                delay += 1000;
            }
```

封装成方法调用

```js
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
        }
```

## 组件的引用

```vue
<template>
    <div>

        <hr>
        <h1>下方是子组件的内容</h1>
      <Son></Son>

    </div>
</template>

<script>
import Son from "./Son";

export default {
    name: 'Father',
    data() 
  			return {}
    },
    components: {
        Son
    }
}
</script>
```

## 组件传值

### 父传子

1.在父组件中定义值，并通过 `:message="parentMessage"` 将数据 `parentMessage` 传递给子组件

```vue
<template>
  <div>
    <h1>父组件</h1>
    <Son :message="parentMessage" />
  </div>
</template>

<script>
import Son from './Son.vue';

export default {
  name: 'Father',
  components: {
    Son
  },
  data() {
    return {
      parentMessage: '这是来自父组件的消息'
    };
  }
};
</script>
```

2.在子组件中利用 `props: ['message']` 接收父组件传递的数据

```vue
<template>
    <div>
      <h2>子组件</h2>
      <p>{{ message }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Son',
    props: {
        message: String
    },
    data () {
        return {}
    }
  };
  </script>
  
```

### 子传父

1.子组件通过 `$emit` 方法触发一个自定义事件 `childMessage`，并携带数据 `这是子组件的数据` 。

```vue
<template>
    <div>
      <h2>子组件</h2>
      <p>{{ message }}</p>
      <button @click="sendMessage">传递给父组件</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Son',
    props: {
        message: String
    },
    data () {
        return {}
    },
    methods: {
    sendMessage() {
      this.$emit('childMessage', '这是子组件传递给父组件的数据');
    }
  }
}
  </script>
  
```

2.父组件通过在子组件上监听 `childMessage` 事件，并在事件处理函数 `handleChildMessage` 中接收数据，存储在 `childMessage` 变量中，最后输出变量即可

```vue
<template>
  <div>
    <h1>父组件</h1>
    <Son @childMessage="handleChildMessage" />
    <p>子组件传递过来的数据: {{ childMessage }}</p>
  </div>
</template>

<script>
import ChildComponent from './ChildComponent.vue';

export default {
  name: 'Father',
  components: {
    ChildComponent
  },
  data() {
    return {
      childMessage: ''
    };
  },
  methods: {
    handleChildMessage(message) {
      this.childMessage = message;
    }
  }
}
</script>

```

>`$emit` 是 Vue 实例中的一个方法，用于触发自定义事件并传递数据。
>
>它通常在子组件中使用，以便向父组件传递数据或通知父组件发生了某些事件。
>
>当子组件使用 `$emit` 触发一个自定义事件时，父组件可以通过在子组件上监听该事件来接收数据或执行相应的操作。
>
>`$emit` 方法接受两个参数：事件名称和要传递的数据。

## VueX

是 Vue.js 的状态管理库，可以集中管理应用的状态，并提供了一种可预测的方式来更新状态。通过他来进行非父子组件之间的传值

1.安装

```bash
npm install vuex@3 
```

2.在`store/index.js`文件夹中，创建数据

```js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
    modules:{
        
    }
})
```

3.在全局配置文件`main.js`中引入

```js
import store from './store';

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
```

4.创建一个存储`store/set.js`，用于管理应用的状态。

```js
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
```

5.在`store/index.js`文件夹中声明`set.js`

```js
import Vue from 'vue'
import Vuex from 'vuex'
import set from './set'

Vue.use(Vuex)
export default new Vuex.Store({
    modules:{
        set
    }
})
```

6.在一个组件当中获取需要传递的数据

```vue
<template>
    <div>
        <input type="text" v-model="info" @input="onInput" />
    </div>
</template>

<script>
export default {
    name: 'Father',
    data() {
        return {
            info: ''
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
```

7.在另外一个组件当中接收

```vue
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
    data() {
        return {}
    },
    computed: {
        value() {
            return this.$store.state.set.value;
        }
    }
}
</script>
```

