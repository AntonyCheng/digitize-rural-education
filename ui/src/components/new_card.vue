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
    export default{
        name:'card'
    }
    </script>