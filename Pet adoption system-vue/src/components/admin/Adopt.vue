<template>
  <div style="height: 80vh">
    <h1 style="text-align: center;font-size: 36px">领养申请</h1>

    <el-row style="margin-top: 15px">
      <el-table ref="adoptTable" :data="user" border height="524px" max-height="800">
        <el-table-column align="center" label="序号" type="index" width="60"/>
        <el-table-column align="center" label="姓名" prop="name"/>
        <el-table-column align="center" label="年龄" prop="age"/>
        <el-table-column align="center" label="住址" prop="address"/>
        <el-table-column align="center" label="电话" prop="phone"/>
        <el-table-column align="center" label="宠物类型" prop="petType"/>
        <el-table-column align="center" label="理由" prop="reason"/>
        <el-table-column align="center" label="申请时间" prop="created_at" width="200px"/>
        <el-table-column align="center" label="操作" width="210">
          <template #default="scope">
            <el-button size="small" type="success" @click="doConsent(scope.row.name)">同意</el-button>
            <el-button size="small" type="danger" @click="doDelete(scope.row.name)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

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
import {onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";

const user = ref([])

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
  axios.get(`http://localhost:8080/api/admin/adopt?page=${page}&pageSize=${size}`)
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

const doConsent = (name) => {
  ElMessageBox.confirm(
      `是否同意[${name}]的申请？`,
      '提示',

      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        axios.post('http://localhost:8080/api/admin/adopt/delete', {name: name})
            .then(response => {
              ElMessage({
                type: 'success',
                message: '删除成功',
              })
              fetchPets();
            })
            .catch(error => {
              ElMessage.error('请求失败，请联系管理员。')
            });
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消删除',
        })
      })
}

const doDelete = (name) => {
  ElMessageBox.confirm(
      `是否确认要删除[${name}]？`,
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        axios.post('http://localhost:8080/api/admin/adopt/delete', {name: name})
            .then(response => {
              ElMessage({
                type: 'success',
                message: '删除成功',
              })
              fetchPets();
            })
            .catch(error => {
              ElMessage.error('请求失败，请联系管理员。')
            });
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消删除',
        })
      })
}

</script>

<style scoped>

</style>