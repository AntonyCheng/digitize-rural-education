<template>
    <div id="app">
      <main>
        <!-- 主要内容区域 -->
        <header>
          <!-- 页面头部内容 -->
          <Header :username="this.name" />
          <router-link to="/userMain/sharing" class="link">共享资源</router-link>&nbsp;&nbsp;
          <router-link to="/userMain/fourC" class="link" >4C微课</router-link>&nbsp;&nbsp;
          <router-link to="#" class="link">数字助手</router-link>
        </header>
        <section>
          <div class="main">
          <!-- 主要内容 -->
          <RouterView></RouterView>
        </div>
        </section>
        <footer>
          <!-- 页面底部内容 -->
          <p>用户界面</p>
        </footer>
      </main>
    </div>
  </template>
  
  <script>
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
    Header,
  },
    methods:{

      openForm(){
        this.$router.push(
      {
      //添加需要传值到那个页面的路径
      path:'/userMain/sharing', 
      //携带需要传递的参数
      query:{dialogFormVisible:true}
      })
      },
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
  padding: 0px;
  background-color: rgba(247,248,249,var(--tw-bg-opacity)); /* 主要内容区域背景颜色 */
  }
  .main{
    background-color: rgb(255, 255, 255);
    height: 100%;
    width: 50%;
    margin: auto;
  }
  .link{
    list-style: none;
    color: rgb(44, 224, 164);
    text-decoration: none;
  }
  </style>
  