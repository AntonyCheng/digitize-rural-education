<template>
    <div class="header">
      <el-dropdown>
        <div class="avatar">{{ userInitial }}</div>
        <arrow-down />
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>我的信息</el-dropdown-item>
        <el-dropdown-item @click.native="toParent">微课上传</el-dropdown-item>
        <el-dropdown-item @click.native="exit">退出</el-dropdown-item>

      </el-dropdown-menu>
    </template>
  </el-dropdown>

      <div class="username">{{ username }}</div>
    </div>
  </template>
  
  <script>
  import Axios from '@/axios/axiosInstance';
  import { ArrowDown } from '@element-plus/icons-vue'
  import { ElMessage } from 'element-plus'
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
    },
    methods:{
      toParent(){
        this.$parent.openForm()
        
      },
          exit(){
           Axios({
        url: '/api/auth/logout',
        method: 'delete',
      }).then((res)=>{
        console.log(res)
      })
      ElMessage({
        message: '退出成功',
        type: 'success',
      })
      // 清除本地存储的用户信息等
      localStorage.clear();
            // 执行退出操作
      this.$router.push('/login');
          }
    }
  };
  </script>
  
  <style>
  .example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
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