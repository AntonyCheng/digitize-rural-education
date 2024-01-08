<template>
    <div id="app">
      <form class="registration-form" @submit.prevent="registerUser()">
        <h2>用户注册</h2>
        <div class="form-group">
          <label for="account">账户：</label>
          <input type="text" id="account" v-model="account" required>
        </div>
        <div class="form-group">
          <label for="password">密码：</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div class="form-group">
          <label for="checkPassword">确认密码：</label>
          <input type="password" id="checkPassword" v-model="checkPassword" required>
        </div>
        <div class="form-group">
          <label for="username">用户名：</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div class="form-group">
          <label for="school">所属学校：</label>
          <input type="text" id="school" v-model="school" required>
        </div>
        <button type="submit">注册</button>
        <br>
        <br>
        <button @click="returnLogin">取消</button>
      </form>
    </div>
  </template>
  
  <script>
 import Axios from '@/axios/axiosInstance';
 import { ElMessage } from 'element-plus'
  export default {
    name: 'RegistrationForm',
    data() {
      return {
        account: '',
        password: '',
        username: '',
        school: '',
        checkPassword:''
      };
    },
    methods: {
      registerUser() {
        // 在这里执行注册逻辑，发送数据到后端或进行其他操作
        // 例如：向后端发送注册请求
      Axios({
        url:'/api/auth/register',
        method:'post',
        data:{
            'account': this.account,
            'password': this.password,
            'name': this.username,
            'checkPassword':this.checkPassword,
            'school': this.school
        }
      }).then((res)=>{
        if(res.data.code=='200'){
          ElMessage({
        message: res.data.msg,
        type: 'success',
      })
      this.$router.push('/login');
        }
        else{
          ElMessage({
        message: res.data.msg,
        type: 'error',
      })
        }
      })


      },
      returnLogin(){
        this.$router.push('/login');
      }
    }
  };
  </script>
  
  <style scoped>
  #app {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-color: #f0f8ff; /* 浅蓝色背景 */
  }
  
  .registration-form {
    width: 300px;
    padding: 20px;
    background-color: #fff; /* 白色背景 */
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #007acc; /* 浅蓝色标题 */
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    color: #333;
  }
  
  input {
    width: calc(100% - 10px);
    padding: 8px;
    border-radius: 4px;
    border: 1px solid #ccc;
  }
  
  button {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 4px;
    background-color: #007acc; /* 按钮背景色 */
    color: white; /* 按钮文字颜色 */
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #005580; /* 鼠标悬停时的背景色 */
  }
  </style>