<template>
  <el-table :data="tableData" style="width: 100%; height: 100%;" max-height="800" >
    <el-table-column fixed prop="id" label="id" width="380" />
    <el-table-column prop="account" label="Account" width="260" />
    <el-table-column prop="name" label="Name" width="260" />
    <el-table-column prop="role" label="Role" width="160" />
    <el-table-column prop="school" label="School" width="260" />
    <el-table-column prop="createTime" label="CreateTime" width="280" />
    <el-table-column fixed="right" label="Operations" width="220">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="resetDefault(scope.$index)"
        >
          重置密码
        </el-button>
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="resetNewForm(scope.$index)"
        >
          修改密码
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
  <el-dialog v-model="dialogFormVisible" title="修改密码" width="500">
    <el-form :model="form">
      <el-form-item label="新密码" :label-width="formLabelWidth">
        <el-input v-model="newPassword" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="resetNew()">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script >
import Axios from '@/axios/axiosInstance';
import { ElMessage } from 'element-plus'
export default {
data() {
  return {
    currentId:'', //当前操作对象id
    newPassword:'',
    dialogFormVisible:false,
    pageSize: 10,
    currentPage:1,//当前页数
    tableData: [],
  };
},
mounted() {
  this.getData();
},
methods: {
  handleCurrentChange(newPage) {
 console.log(newPage)
 //把最新的页码（newPage）赋值给 动态的 pagenum 
   this.currentPage = newPage
  //获取到最新显示的页码值  重新发送axios请求 这里是封装好的请求方法
  this.getData();
  },
  resetDefault(index){
    console.log(this.tableData[index].id);
    Axios.put(`/api/admin/user/reset/default/${this.tableData[index].id}`, null, {
  headers: {
    'token': 'your_token_value_here'
  }
})
.then(response => {
  console.log('Response:', response.data);
  // 处理返回的数据
  ElMessage({
    message: '重置成功',
    type: 'success',
  });
})
.catch(error => {
  console.error('Error:', error);
  // 处理错误
  ElMessage({
    message: '重置失败',
    type: 'error',
  });
});
  },
  resetNewForm(index){
    this.dialogFormVisible=true;
    this.currentId=this.tableData[index].id;
  },
  resetNew(){
    Axios({
      url:'/api/admin/user/reset/new',
      method:'put',
      params:{
        'id':this.currentId,
        'newPassword':this.newPassword
      }
    }).then((res)=>{
      console.log(res);
        ElMessage({
      message: "密码修改成功",
      type: 'success',
               })
    })
  },
  async getData() {
    try {
      const response = await Axios.get('/api/admin/user/page', {
        params: {
          page: this.currentPage,
          size: this.pageSize
        },
        headers: {
          Authorization: this.token
        }
      });
    this.tableData=response.data.data.records;
    console.log(this.tableData)
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  },
  async handlePageChange(page) {
    this.currentPage = page;
    await this.getData();
  }
}
};

</script>
<style>
.pagination{
  margin-top: 10px;
  height: 40px;
  background-color: #fff;
}
</style>
