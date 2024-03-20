<template>
  <div id="app">
    <h1>{{ pageTitle }}</h1>
    <button @click="handleButtonClick">登录</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageTitle: '资源共享平台'
    };
  },
  methods: {
    handleButtonClick() {
      // 跳转登录界面
      window.open('/login', '_blank');
    }
  }
};
</script>

<style scoped>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background-color: #f0f8ff; /* 浅蓝色背景 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#app {
  text-align: center;
}

h1 {
  color: #007acc; /* 浅蓝色标题 */
  font-size: 4em; /* 减小字体大小 */
  margin-bottom: 30px; /* 增加标题底部间距 */
}

button {
  padding: 15px 30px;
  font-size: 1.2em; /* 按钮字体大小 */
  background-color: #007acc; /* 按钮背景色 */
  color: white; /* 按钮文字颜色 */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 20px; /* 增加按钮与标题的间距 */
}

button:hover {
  background-color: #005580; /* 鼠标悬停时的背景色 */
}
</style>
