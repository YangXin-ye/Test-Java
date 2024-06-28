# 新建页面

## 文件

![image-20240611130151104](./images/image-20240611130151104.png)

## 页面

![image-20240611130433333](./images/image-20240611130433333.png)

# Echarts

## 安装

```bash
npm install echarts
```

## 页面

1.放置样式

```html
<template>
    <el-card style=" margin-top: 20px">
        <div ref="echarts" style="height:300px;"></div>
    </el-card>
</template>
```

2.引入`Echarts`

```js
import * as echarts from 'echarts';
```

3.基于准备好的dom，初始化echarts实例

```js
 mounted() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(this.$refs.echarts);
 } 
```

4.官网复制配置项

```js
var option = {
            	// ...
        };
```

5.绑定数据

```js
        myChart.setOption(option);
```

![image-20240611132009541](./images/image-20240611132009541.png)

