<template>
  <div style="height: 80vh">
    <h1 style="text-align: center;font-size: 36px">领养情况</h1>
    <!-- 数据表 -->
    <el-row style="margin-top: 15px">
      <!-- 定义数据表  data属性用来绑定我们要显示的数据列表-->
      <el-table
          ref="studentTable"
          :data="user"
          border="true"
          height="524px"
          max-height="800"
          stripe="true"
          @select="doSelecte"
      >

        <el-table-column align="center" type="selection" width="50"/>
        <el-table-column align="center" label="姓名" prop="user_name"/>
        <el-table-column align="center" label="用户名" prop="user_username"/>
        <el-table-column align="center" label="宠物名称" prop="pet_name"/>
        <el-table-column align="center" label="宠物类型" prop="pet_type"/>
        <el-table-column align="center" label="操作" width="210">
          <template #default="scope">
            <el-button size="small" type="primary" @click="openEditWin(scope.row.stuNo)">编辑</el-button>
            <el-button size="small" type="danger" @click="doDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <!-- 分页栏 -->
    <el-row style="position: absolute;bottom: 20px">
      <el-pagination
          :current-page="pagination.page"
          :page-size="pagination.size"
          :total="total"
          background
          layout="prev, pager, next"
          @current-change="current_change"
      >
      </el-pagination>
    </el-row>
  </div>
</template>

<script setup>
import {computed, onMounted, ref, watch} from "vue";
import axios from "axios";
import {CirclePlus, Delete, Edit, Search, Share} from "@element-plus/icons-vue";

const user = ref([])

const doSearch = ref(false)

const searchTotal = ref(false)

const search = () => {
  doSearch.value = true;
  searchTotal.value = true;
}

const pagination = ref({
  page: 1,
  size: 10,
})

const total = ref();

const current_change = (page) => {
  pagination.value.page = page;
  fetchPets();
};

const fetchPets = () => {
  const {page, size} = pagination.value;
  axios.get(`http://localhost:8080/api/admin/adoption?page=${page}&pageSize=${size}`)
      .then(response => {
        user.value = response.data.data;
        total.value = response.data.total;
      })
      .catch(error => {
        console.error('请求错误!');
      });
};

onMounted(() => {
  fetchPets();
});

const query = ref({
  name: '',
  species: '',
  available: ''
});

watch(query, () => {
  pagination.value.page = 1;
});


</script>

<style scoped>

</style>