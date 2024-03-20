<template>
  <div>
    <el-card v-for="(item, index) in tableData" :key="index" class="custom-card" @click.native="goToParticulars(tableData[index].id)">
      <span slot="header" class="card-title">{{ item.title }}</span>
      <p class="card-description">{{ item.info }}</p>
      <a class="card-link" :href="item.link" target="_blank">View Details</a>
    </el-card>
  </div>
</template>
<script>
import Axios from '@/axios/axiosInstance'
export default{
    name:"4C",
    data(){
        return{
    pageSize: 10,
    currentPage:1,//当前页数
    tableData: [],
        }
    },
    mounted() {
      this.getSrourceList();
      

    },
    methods:{
      goToParticulars(id){
        this.$router.push(
      {
      //添加需要传值到那个页面的路径
      path:'/userMain/particulars', 
      //携带需要传递的参数
      query:{id:id}
      })
      },
      getSrourceList(){
        Axios({
          url: 'http://localhost:38080/api/user/resource/page_fourc',
          method:'get',
          params: {
              page: this.currentPage,
              size: this.pageSize
        },
        }).then((res)=>{
          console.log(res)
          this.tableData=res.data.data.records;
        })
      },
    }
}

</script>

<style>
.custom-card {
  margin-bottom: 5px; /* 设置卡片之间的间距 */
  height: 140px;
}

.card-title {
  font-weight: bold; /* 标题加粗 */
  display: block; /* 确保标题独占一行 */
  float: left;
}

.card-description {
  margin-top: 30px; /* 设置描述文字与标题之间的间距 */
  text-align: left; /* 左对齐 */

}

.card-link {
  float: right; /* 将链接右浮动 */
  margin-top: 10px; /* 设置链接与描述文字之间的间距 */
}
.el-card:hover {
box-shadow: 0 1px 6px rgba(255, 255, 255, 0.932);
border-color: #eee;
transition: all 0.2s ease-in-out;
}
</style>