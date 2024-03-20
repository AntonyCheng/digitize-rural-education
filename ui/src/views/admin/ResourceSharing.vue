<template>
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
</template>
<script>
import Axios from '@/axios/axiosInstance';
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
      getFileList(){
        Axios({
            url: 'http://localhost:38080/api/admin/resource/page_share',
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
}</script>