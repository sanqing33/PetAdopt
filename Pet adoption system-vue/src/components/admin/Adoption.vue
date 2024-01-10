<template>
  <div style="height: 80vh">
    <h1 style="text-align: center;font-size: 36px">领养情况</h1>
    <el-row style="margin-top: 15px">
      <el-table ref="studentTable" :data="user" height="524px" max-height="800">
        <el-table-column align="center" type="selection" width="50"/>
        <el-table-column align="center" label="姓名" prop="user_name"/>
        <el-table-column align="center" label="用户名" prop="user_username"/>
        <el-table-column align="center" label="宠物名称" prop="pet_name"/>
        <el-table-column align="center" label="宠物类型" prop="pet_type"/>
        <el-table-column align="center" label="操作" width="210">
          <template #default="scope">
            <el-button size="small" type="primary" @click="doEdit(scope.row.name)">编辑</el-button>
            <el-button size="small" type="danger" @click="doDelete(scope.row.user_username)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-dialog v-model="editDialogVisible" title="编辑宠物信息" width="60%">
      <el-form ref="form" :model="edit" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="edit.user_name" placeholder="姓名" readonly></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="edit.user_username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="宠物名称">
          <el-input v-model="edit.pet_name" placeholder="宠物名称"></el-input>
        </el-form-item>
        <el-form-item label="宠物类型">
          <el-input v-model="edit.pet_type" placeholder="宠物类型"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="doEditSubmit">提交</el-button>
        <el-button type="danger" @click="hidEditWin">取消</el-button>
      </template>
    </el-dialog>

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


const edit = ref()

const editDialogVisible = ref(false)

const doEdit = (name) => {
  edit.value = {};
  editDialogVisible.value = true;
  for (let i = 0; i < user.value.length; i++) {
    if (user.value[i].name === name) {
      edit.value = user.value[i];
      break;
    }
  }
}

const doEditSubmit = () => {
  axios.post('http://localhost:8080/api/admin/adoption/edit', edit.value)
      .then(response => {
        ElMessage({
          type: 'success',
          message: '修改成功',
        })
        editDialogVisible.value = false;
      })
      .catch(error => {
        ElMessage.error('请求失败，请联系管理员。')
      });
}

const hidEditWin = () => {
  editDialogVisible.value = false;
  edit.value = {};
}

const doDelete = (user_username) => {
  ElMessageBox.confirm(
      `是否确认要删除[${user_username}]？`,
      '提示',

      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        axios.post('http://localhost:8080/api/admin/adoption/delete', {name: user_username})
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