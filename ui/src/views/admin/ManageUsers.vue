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
            @click.prevent="deleteRow(scope.$index)"
          >
            修改密码
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
    <el-pagination
    :page-size="5"
    :pager-count="11"
    layout="prev, pager, next"
    :total="1000"
  />
    </div>
  </template>
  <script >
import Axios from '@/axios/axiosInstance';
export default {
  data() {
    return {
      pageSize: 5,
      pagerCount: 11,
      totalItems: 0,
      currentPage: 1,
      tableData: [],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    resetDefault(index){
      console.log(this.tableData[index].id);
      Axios.put(`/api/admin/user/reset/default/${this.tableData[index].id}`)
    .then(response => {
    console.log('Response:', response.data);
    // 处理返回的数据
     })
   .catch(error => {
    console.error('Error fetching data:', error);
  });
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

      // console.log(response.data.data.records);
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
    margin-top: 4px;
    background-color: #fff;
  }
  </style>
