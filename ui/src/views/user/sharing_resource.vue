<template>
  <div class="control">
        <el-button @click="dialogFormVisible = true" class="btn">上传共享资源</el-button>
    </div>

    <el-dialog v-model="dialogFormVisible" title="共享课程" width="500">
        <span>请输入资源标题</span>
        <el-input></el-input>
        <el-upload
        class="upload-demo"
        drag
         action="http://127.0.0.1:4523/m1/4130431-0-default/user/file/upload_file"
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
        <el-button type="primary" @click="">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>

</template>
<script>
import { UploadFilled } from '@element-plus/icons-vue'
export default{
    name:"4C",
    data(){
        return{
    dialogFormVisible:false,
        }
    }
}

</script>
