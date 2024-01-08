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
        <Header :username="this.name" />
      </header>
      <section>
        <!-- 主要内容 -->
        <RouterView></RouterView>
      </section>
      <footer>
        <!-- 页面底部内容 -->
        <p>管理员管理</p>
      </footer>
    </main>
  </div>
</template>

<script>
import Navigation from '@/components/navigation.vue'
import Header from '@/components/header.vue'
export default {
  name: 'adminMain',
  data() {
    return {
      name:'',
    };
  },
  mounted() {
    // 在组件挂载到 DOM 后执行的操作
    this.created();
  },
  components: {
  Navigation, // 注册导航组件
  Header,
},
  methods:{
    created() {
         const cachedData = localStorage.getItem('cachedData');
            if (cachedData) {
            this.cachedData = JSON.parse(cachedData); // 从 LocalStorage 获取数据
             console.log(this.cachedData);
             console.log(this.cachedData.data.name);
             this.name=this.cachedData.data.name
             console.log(this.name);
                   } else {
                    console.log("error")
           }
  },
}
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
