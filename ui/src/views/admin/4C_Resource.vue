<template>
    <div class="control">
        <el-button @click="dialogFormVisible = true" class="btn">上传4C资源</el-button>
    </div>
    <div style="margin: 10px 0" />
    <el-table :data="tableData" style="width: 100%; height: 100%;" max-height="800" >
    <el-table-column fixed prop="id" label="id" width="400" />
    <el-table-column prop="fileName" label="fileName" width="280" />
    <el-table-column label="url" width="240">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>
            <div>{{ scope.row.fileUrl }}</div>
          </template>
          <template #reference>
            <el-tag>url</el-tag>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column prop="info" label="info" width="160" />
    <el-table-column prop="title" label="title" width="260" />
    <el-table-column prop="userAccount" label="userAccount" width="280" />
    <el-table-column fixed="right" label="Operations" width="220">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="console.log(scope)"
        >
          
        </el-button>
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent=""
        >
          
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="pagination">
    <el-pagination @current-change="handleCurrentChange"
    :page-size=this.pageSize
    :current-page=this.currentPage
    layout="prev, pager, next"
    :total="1000"
  />
  </div>
    <el-dialog v-model="dialogFormVisible" title="新增4C课程" width="500">
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
         action="http://localhost:38080/api/admin/file/upload_fourc_file"
         with-credentials
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
import Axios from '@/axios/axiosInstance';
import { UploadFilled } from '@element-plus/icons-vue'
export default{
    name:"4C",
    data(){
        return{
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
      this.getFileList();
    },
    methods:{
      handleCurrentChange(newPage) {
 console.log(newPage)
 //把最新的页码（newPage）赋值给 动态的 pagenum 
   this.currentPage = newPage
  //获取到最新显示的页码值  重新发送axios请求 这里是封装好的请求方法
  this.getFileList();
  },
      upLoadFile(){
          Axios({
            url: 'http://localhost:38080/api/admin/resource/add_fourc',
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
      getFileList(){
        Axios({
            url: 'http://localhost:38080/api/admin/resource/page_fourc',
            method: 'get',
            params: {
              page: this.currentPage,
              size: this.pageSize
        },
          }).then((res)=>{
            console.log(res);
            this.tableData=res.data.data.records;
          })
      }
    }
}

</script>
<style scoped>
.control{
    background: #ffffff;
    border-radius: 6px;
    margin-top: -8px;
    width: 100%;
    height: 40px;
}
.control .btn{
        margin-top: 5px;
        margin-right: 10px;
        float: right;
}
.pagination{
  margin-top: 10px;
  height: 40px;
  background-color: #fff;
}
</style>