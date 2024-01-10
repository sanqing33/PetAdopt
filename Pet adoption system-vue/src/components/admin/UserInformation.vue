<template>
  <div style="height: 80vh">
    <h1 style="text-align: center;font-size: 36px">用户信息管理</h1>

    <el-row style="margin-top: 15px;display: block;background: white;padding: 20px">
      <el-row style="justify-content: center">
        <el-button :icon="Delete" type="primary" @click="doDeletes">删除</el-button>
        <el-button :icon="Search" type="primary" @click="search">搜索</el-button>
      </el-row>
    </el-row>

    <el-dialog v-model="doSearch" title="搜索" width="30%">
      <el-row style="text-align: center">
        <el-form :inline="true" :model="query" class="demo-form-inline">
          <el-form-item label="姓名">
            <el-input v-model="query.name" placeholder="姓名" style=""></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="query.username" placeholder="用户名" style="transform: translateX(-7px)"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="query.email" placeholder="邮箱" style=""></el-input>
          </el-form-item>
        </el-form>
      </el-row>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="doSearch = false">返回</el-button>
        <el-button type="primary" @click="doQuery">查询</el-button>
      </span>
      </template>
    </el-dialog>

    <el-row style="margin-top: 15px">
      <el-table ref="userTable" :data="user" height="524px" max-height="800" @selection-change="Select">
        <el-table-column align="center" type="selection" width="50"/>
        <el-table-column align="center" label="姓名" prop="name"/>
        <el-table-column align="center" label="用户名" prop="username"/>
        <el-table-column align="center" label="密码" prop="password"/>
        <el-table-column align="center" label="邮箱" prop="email"/>
        <el-table-column align="center" label="注册时间" prop="created_at"/>
        <el-table-column align="center" label="操作" width="210">
          <template #default="scope">
            <el-button size="small" type="primary" @click="doEdit(scope.row.name)">编辑</el-button>
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

    <el-dialog v-model="editDialogVisible" title="编辑用户信息" width="60%">
      <el-form ref="form" :model="edit" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="edit.name" placeholder="姓名" readonly></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="edit.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="edit.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="edit.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="注册时间">
          <el-input v-model="edit.created_at" disabled placeholder="注册时间"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="doEditSubmit">提交</el-button>
        <el-button type="danger" @click="hidEditWin">取消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {computed, onMounted, ref, watch} from "vue";
import axios from "axios";
import {CirclePlus, Delete, Edit, Search, Share} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";

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
  if (searchTotal.value === true) {
    doQuery();
  } else {
    fetchPets();
  }
};

const fetchPets = () => {
  const {page, size} = pagination.value;
  axios.get(`http://localhost:8080/api/admin/user?page=${page}&pageSize=${size}`)
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
  axios.post('http://localhost:8080/api/admin/user/edit', edit.value)
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
  insertDialogVisible.value = false;
  insert.value = {};
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
        axios.post('http://localhost:8080/api/admin/user/delete', {name: name})
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

const query = ref({
  name: '',
  username: '',
  email: ''
});

const doQuery = () => {
  const {name, username, email} = query.value;
  const {page, size} = pagination.value;

  const params = {};
  if (name) {
    params.name = name;
  }
  if (username) {
    params.username = username;
  }
  if (email) {
    params.email = email;
  }

  axios.get(`http://localhost:8080/api/admin/user/query?page=${page}&pageSize=${size}`, {params})
      .then(response => {
        user.value = response.data.data;
        total.value = response.data.total;
      })
      .catch(error => {
        console.error('请求错误!');
      });

  doSearch.value = false
}

watch(query, () => {
  pagination.value.page = 1;
  doQuery();
});


const selectedData = ref()

const Select = (selection) => {
  selectedData.value = selection.map(item => item.username);
}

const doDeletes = () => {
  console.log(selectedData)
  ElMessageBox.confirm(
      `是否确认要删除选中的宠物信息？`,
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        axios.post('http://localhost:8080/api/admin/user/deletes', selectedData.value)
            .then(response => {
              ElMessage({
                type: 'success',
                message: '删除成功',
              })
              fetchPets();
            })
            .catch(error => {
              ElMessage.error('选中存在已领养宠物的用户，无法删除。')
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