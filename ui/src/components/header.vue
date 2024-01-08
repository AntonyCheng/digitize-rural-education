<template>
    <div class="header">
      <div class="avatar">{{ userInitial }}</div>
      <div class="username">{{ username }}</div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Header',
    props: {
      username: {
        type: String,
        required: true
      }
    },
    computed: {
      userInitial() {
        // 获取用户名的首字母作为头像
        return this.username ? this.username.charAt(0).toUpperCase() : '';
      }
    }
  };
  </script>
  
  <style>
  .header {
    display: flex;
    align-items: center;
    font-family: Arial, sans-serif;
    padding: 10px;
    background-color: #eee;
    justify-content: flex-end; /* 右浮动 */
  }
  
  .avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #3498db;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: #fff;
    margin-left: 10px; /* 调整头像和用户名之间的距离 */
  }
  
  .username {
    font-size: 18px;
    font-weight: bold;
    margin-right: 10px; /* 调整名字和页面右侧的距离 */
  }
  </style>