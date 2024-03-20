<template>
    <div class="container">
      <!-- 资源详情 -->
            <div class="resource-title">
        <h2>{{ this.title }}</h2>
      </div>
      <div class="resource-details">
        <h1></h1>
        <!-- 这里放置资源详情内容 -->
        <video :src="url" controls class="video_player"></video>
      </div>
  
      <!-- 资源标题 -->

  
      <!-- 资源简介 -->
      <div class="resource-description">
        <h3>资源简介</h3>
        <!-- 这里放置资源简介内容 -->
        <p>{{ this.info }}</p>
        <p>上传者:{{ this.userAccount }}</p>
      </div>
  
      <!-- 评论区 -->
      <div class="comments">
        <h3>评论区</h3>
        <!-- 这里可以放置评论组件 -->
        <!-- 示例： -->
        <div v-for="(comment, index) in comments" :key="index" class="comment">
          <!-- 上划线 -->
          <div class="divider"></div>
          <p>{{ comment.text }}</p>
          <p class="comment-author">作者：{{ comment.author }}</p>
          <!-- 下划线 -->
          <div class="divider"></div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
import Axios from '@/axios/axiosInstance';

  export default {
    data() {
      return {
        title:'',
        info:'',
        url:'',
        userAccount:'',
        tableData:[],
        // 示例评论数据
        comments: [
          { text: '这是一个很棒的资源！', author: '张三' },
          { text: '谢谢分享，收藏了！', author: '李四' },
          { text: '有点难度，但很有收获！', author: '王五' }
        ]
      };
    },
    mounted(){
        this.getResourcePart(this.$route.query.id)
    },
    methods:{
        getResourcePart(id){
            console.log(id)
            Axios({
                url:'http://localhost:38080/api/user/resource/detail/'+id,
                method:'get'
            }).then((res)=>{
                this.title=res.data.data.title
                this.info=res.data.data.info
                this.url=res.data.data.fileUrl
                this.userAccount=res.data.data.userAccount
            })
        }
    }
  };
  </script>
  
  <style scoped>
  .resource-title h2{
    margin-top: 10px;
   line-height: 30px;
   font-size: 30px;

  }
  .video_player{
    margin: auto;
    width: 100%;
  }
  
  .resource-details,
  .resource-title,
  .resource-description,
  .comments {
    text-align: left;
    margin-bottom: 20px; /* 设置各部分之间的间距 */
  }
  
  .resource-details h1,
  .resource-title h2,
  .resource-description h3,
  .comments h3 {
    margin-top: 0; /* 移除各部分标题的默认顶部间距 */
  }
  
  .divider {
    border-top: 1px solid #ccc; /* 下划线 */
    margin-top: 1px; /* 设置下划线与内容之间的间距 */
    margin-bottom: 1px; /* 设置下划线与内容之间的间距 */
  }
  .container{
    background-color: rgb(255, 255, 255);
  }
  </style>
  