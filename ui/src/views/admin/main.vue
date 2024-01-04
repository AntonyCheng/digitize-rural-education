<template>
  <div id="app">
    <aside class="sidebar">
      <!-- 左侧导航栏内容 -->
        <navigation></navigation>
    </aside>
    <main>
      <!-- 主要内容区域 -->
      <header>
        <!-- 页面头部内容 -->
        <h1>页面标题</h1>
      </header>
      <section>
        <!-- 主要内容 -->
        <RouterView></RouterView>
      </section>
      <footer>
        <!-- 页面底部内容 -->
        <p>页脚信息</p>
      </footer>
    </main>
  </div>
</template>

<script>
import Navigation from 'ui/src/components/navigation.vue'
export default {
  name: 'Main',
  components: {
  Navigation, // 注册导航组件
},
}
</script>

<style scoped>
/* 做一些基本的样式重置或者设置 */
* {
margin: 0;
padding: 0;
box-sizing: border-box;
}

body,
#app {
width: 100vw;
height: 100vh;
font-family: Arial, sans-serif;
display: flex;
}

/* 左侧导航栏样式 */
.sidebar {
flex: 0 0 auto;
width: 200px;
background-color: #f0f0f0; /* 左侧导航栏背景颜色 */
padding: 20px;
}

.sidebar ul {
list-style: none;
padding: 0;
}

.sidebar ul li {
margin-bottom: 10px;
}

.sidebar ul li a {
text-decoration: none;
color: #333;
}

/* 主要内容区域样式 */
main {
flex: 1 1 auto;
display: flex;
flex-direction: column;
}

header,
footer {
background-color: #f0f0f0; /* 页面头部和页脚背景颜色 */
padding: 20px;
}

section {
flex: 1 1 auto;
overflow-y: auto;
padding: 20px;
background-color: #add8e6; /* 主要内容区域背景颜色 */
}
</style>
