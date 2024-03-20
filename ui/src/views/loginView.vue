<template>
  <div id="app">
    <div class="login-container">
      <h2>{{ pageTitle }}</h2>
      <form @submit.prevent="loginSubmit()">
        <div class="input-group">
          <label for="account">用户名:</label>
          <input type="text" id="account" name="account" required v-model="account">
        </div>
        <div class="input-group">
          <label for="password">密码:</label>
          <input type="password" id="password" name="password" required v-model="password">
        </div>
        <div class="input-group">
          <button class="btn-login" @click="login">登录</button>
            <a href="/register" style="margin-left:185px; color: aqua;">注册</a>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
 import Axios from '@/axios/axiosInstance'
 import { ElMessage } from 'element-plus'
export default {
  name: 'loginView',
  data() {
    return {
      account: '',
      password: '',
      pageTitle: '加数前进乡村支教平台'
    };
  },
  methods: {
    //禁用表单跳转 
    loginSubmit() {
      event.preventDefault()
    },
    //登录方法
    login() {
      Axios({
        url: '/api/auth/login',
        method: 'post',
        data: {
          'account': this.account,
          'password': this.password
        }
      }).then((res) => {
        console.log(res);
        if(res.data.code=='200'){
          ElMessage({
        message: res.data.msg,
        type: 'success',
      })
      localStorage.setItem('cachedData', JSON.stringify(res.data));
      if(res.data.data.role=='admin'){
        // this.$router.push('/adminMain');
        location.replace(" /adminMain/users");
      }else if(res.data.data.role=='user'){
        this.$router.push('/userMain/sharing');
      }

        }
        else{
          ElMessage({
        message: res.data.msg,
        type: 'error',
      })
        }
      })
    }
  }
}
</script>

<style>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background-color: #f0f8ff; /* 浅蓝色背景 */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#app {
  text-align: center;
}

h1 {
  color: #007acc; /* 浅蓝色标题 */
  font-size: 4em; /* 字体大小 */
  margin-bottom: 30px; /* 底部间距 */
}

.login-container {
  width: 400px; /* 表单宽度 */
  background-color: #fff; /* 白色背景 */
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.login-container h2 {
  margin-bottom: 30px;
  color: #007acc; /* 浅蓝色标题 */
  font-size: 2em; /* 字体大小 */
}

.input-group {
  margin-bottom: 20px;
  text-align: left;
}

.input-group label {
  display: block;
  margin-bottom: 8px;
  color: #555;
}

.input-group input[type="text"],
.input-group input[type="password"] {
  width: 100%;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

.input-group .btn-login {
  width: 100%;
  padding: 15px 30px;
  border-radius: 4px;
  border: none;
  background-color: #007acc; /* 按钮背景色 */
  color: white; /* 按钮文字颜色 */
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.input-group .btn-login:hover {
  background-color: #005580; /* 鼠标悬停时的背景色 */
}
</style>
