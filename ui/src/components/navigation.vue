<template>
    <div class="navigation">
      <ul>
        <li @click="navigate('user')">用户管理</li>
        <li @click="navigate('resource')">共享资源管理</li>
        <li @click="navigate('4c')">4C微课管理</li>
        <li @click="navigate('custom')">微课定制管理</li>
        <li @click="navigate('assistant')">数字助手管理</li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name:'Navigation',
    methods: {
      navigate(route) {
        // 根据点击的选项，进行路由导航或其他操作
        switch (route) {
          case 'user':
            // 路由到用户管理页面或者执行其他相关操作
            alert("欢迎来到用户管理")
            break;
          case 'resource':
            // 路由到共享资源管理页面或者执行其他相关操作
            break;
          case '4c':
            // 路由到4C微课管理页面或者执行其他相关操作
            break;
          case 'custom':
            // 路由到微课定制管理页面或者执行其他相关操作
            break;
          case 'assistant':
            // 路由到数字助手管理页面或者执行其他相关操作
            break;
          default:
            break;
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .navigation {
    background-color: #f2f2f2;
    padding: 10px;
  }
  
  ul {
    list-style: none;
    padding: 0;
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
  </style>
  