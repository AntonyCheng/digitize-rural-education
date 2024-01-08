<template>
  <div class="navigation">
    <ul>
      <li @click="navigate('user')">用户管理</li>
      <li @click="navigate('resource')">共享资源管理</li>
      <li @click="navigate('4c')">4C微课管理</li>
      <li @click="navigate('custom')">微课定制管理</li>
      <li @click="navigate('resourceManagement')">资源管理</li>
      <li @click="navigate('assistant')">数字助手管理</li>
    </ul>
    <!-- 退出按钮放在导航栏底部 -->
    <div class="logout" @click="logout">
      <span><i class="fas fa-sign-out-alt"></i> 退出</span>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
  name:'Navigation',
  methods: {
    navigate(route) {
        // 根据点击的选项，进行路由导航或其他操作
        switch (route) {
          case 'user':
            // 路由到用户管理页面或者执行其他相关操作
            this.$router.push('/adminMain/users');
            break;
          case 'resource':
            // 路由到共享资源管理页面或者执行其他相关操作
            this.$router.push('/adminMain/sharing');
            break;
          case '4c':
            // 路由到4C微课管理页面或者执行其他相关操作
            break;
          case 'custom':
            // 路由到微课定制管理页面或者执行其他相关操作
            break;
          case 'resourceManagement':
            break;
          case 'assistant':
            // 路由到数字助手管理页面或者执行其他相关操作
            break;
          default:
            break;
        }
      },
    logout() {
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

<style scoped>
.navigation {
  background-color: #f2f2f2;
  padding: 10px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

ul {
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}

li {
  cursor: pointer;
  padding: 5px 10px;
  margin-bottom: 5px;
  border-radius: 3px;
  transition: background-color 0.3s ease;
}

li:hover {
  background-color: #e0e0e0;
}

.logout {
  cursor: pointer;
  padding: 5px 10px;
  border-radius: 3px;
  transition: background-color 0.3s ease;
}

.logout:hover {
  background-color: #e0e0e0;
}

/* Font Awesome CDN链接，请确保你的项目中已引入对应的图标库 */
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');
</style>
