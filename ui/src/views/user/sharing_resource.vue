<template>
  <div>
    <el-card v-for="(item, index) in tableData" :key="index" class="custom-card" @click.native="goToParticulars(tableData[index].id)">
      <span slot="header" class="card-title">{{ item.title }}</span>
      <p class="card-description">{{ item.info }}</p>
      <a class="card-link" :href="item.link" target="_blank">View Details</a>
    </el-card>
  </div>
  <div class="small-card">
    <h3>最新资源</h3>
    <el-card v-for="(item, index) in tableData" :key="index"  @click.native="goToParticulars(tableData[index].id)" class="small-custom-card">
      <span slot="header" class="card-title">{{ item.title }}</span>
      <p >{{ item.info }}</p>
    </el-card>
  </div>
    <el-dialog v-model="dialogFormVisible" title="上传共享资源" width="500">
      <span>请输入资源标题</span>
    <el-input v-model="title"></el-input>
      <span>请输入资源简介</span>
      <el-input
  v-model="info"
  type="textarea"
  placeholder="Please input"
/>
<div style="margin: 20px 0" />
      <el-upload
      class="upload-demo"
      drag
      name="multipartFile"
      with-credentials
      :limit="sizeLimit"
    :on-exceed="handleExceed"
      :file-list="fileList"
       action="http://localhost:38080/api/user/file/upload_file"
       :on-success="upLoadFileRes"
        multiple
    >
  <el-icon class="el-icon--upload"><upload-filled /></el-icon>
  <div class="el-upload__text">
    Drop file here or <em>click to upload</em>
  </div>
  <template #tip>
    <div class="el-upload__tip">
      jpg/png files with a size less than 500kb
    </div>
  </template>
</el-upload>  
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button type="primary" @click="upLoadFile">
        提交
      </el-button>
    </div>
  </template>
</el-dialog>
</template>
<script>
  import { ElMessage } from 'element-plus'
import Axios from '@/axios/axiosInstance'
import { UploadFilled } from '@element-plus/icons-vue'
export default{
    name:"sharing",
    data(){
        return{
    sizeLimit: 100 * 1024 * 1024, // 100MB，这里以字节为单位
    fileList: [],
    dialogFormVisible:false,
    title:'',
    info:'',
    fileId:'',
    pageSize: 10,
    currentPage:1,//当前页数
    tableData: [],
        }
    },
    mounted() {

      this.getSrourceList();
      this.formState();
      
    },
    methods:{

      handleExceed(files, fileList) {
      this.$message.warning(`文件数量超过限制,请不要上传超过1个文件。`);
    },
    formatSize(size) {
      return (size / 1024 / 1024).toFixed(2) + 'MB';
    },
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
          url: 'http://localhost:38080/api/user/resource/page_share',
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
      formState(){
        this.dialogFormVisible=this.$route.query.dialogFormVisible
      },
      upLoadFile(){
          Axios({
            url: 'http://localhost:38080/api/user/resource/add',
            method: 'post',
            data:{
                'title':this.title,
                'info':this.info,
                'fileId':this.fileId
        }
          }).then((res)=>{
            console.log(res);
            this.dialogFormVisible=false
          ElMessage({
        message: '上传成功',
        type: 'success',
      })
          })
      },
      upLoadFileRes(res){
        console.log(res.data)
        this.fileId=res.data
      },
    }
}

</script>

<style>
.small-custom-card{
  height: 60px;
  width: 200px;
  margin-left: 20px;
  margin-bottom: 5px;
}
.small-card p{
  font-size: 16px;
}
.small-card{
  height: 350px;
  width: 240px;
  position: absolute;
  background: #ffffff;
  right: 270px;
  top: 140px;
  border-radius: 6px;
}
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
.custom-card {
  margin-bottom: 20px; /* 增加卡片之间的间距 */
  height: auto; /* 让卡片高度自适应内容 */
  padding: 20px; /* 增加内边距 */
  border-radius: 10px; /* 圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  transition: box-shadow 0.3s ease; /* 添加过渡效果 */
}

.custom-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* 鼠标悬停时增加阴影效果 */
}

.card-title {
  font-size: 18px; /* 标题字体大小 */
  font-weight: bold; /* 标题加粗 */
  color: #333; /* 标题颜色 */
}

.card-description {
  margin-top: 10px; /* 设置描述文字与标题之间的间距 */
  font-size: 14px; /* 描述文字字体大小 */
  color: #666; /* 描述文字颜色 */
}

.card-link {
  display: inline-block; /* 将链接变为块级元素 */
  margin-top: 20px; /* 设置链接与描述文字之间的间距 */
  color: #1890ff; /* 链接文字颜色 */
  text-decoration: none; /* 去除链接下划线 */
  transition: color 0.3s ease; /* 添加过渡效果 */
}

.card-link:hover {
  color: #40a9ff; /* 鼠标悬停时改变链接颜色 */
}

</style>
