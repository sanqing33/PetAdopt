<template>
  <div style="height: 80vh">
    <h1 style="text-align: center;font-size: 36px">宠物信息管理</h1>

    <el-row style="margin-top: 15px;display: block;background: white;padding: 20px">
      <el-row style="justify-content: center">
        <el-button type="primary" @click="doInsert">
          <el-icon class="el-icon--left">
            <CirclePlus/>
          </el-icon>
          添加
        </el-button>
        <el-button :icon="Delete" type="primary" @click="doDeletes">删除</el-button>
        <el-button :icon="Search" type="primary" @click="search">搜索</el-button>
      </el-row>
    </el-row>

    <el-dialog v-model="doSearch" title="搜索" width="30%">
      <el-row style="text-align: center">
        <el-form :inline="true" :model="query" class="demo-form-inline">
          <el-form-item label="名称">
            <el-input v-model="query.name" placeholder="名称" style="width: 220px"></el-input>
          </el-form-item>
          <el-form-item label="种类">
            <el-select v-model="query.species" placeholder="状态">
              <el-option label="猫" value="猫"></el-option>
              <el-option label="狗" value="狗"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="query.available" placeholder="状态">
              <el-option label="已领养" value="0"></el-option>
              <el-option label="可领养" value="1"></el-option>
              <el-option label="不可领养" value="2"></el-option>
            </el-select>
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
      <el-table ref="petTable" :data="pets" height="524px" max-height="800" @select="Select">
        <el-table-column align="center" type="selection" width="50"/>
        <el-table-column align="center" label="名称" prop="name"/>
        <el-table-column align="center" label="照片" prop="name" width="140px">
          <template #default="scope">
            <img :src="`http://localhost:8080/pet/${scope.row.name}.jpg`" alt="" style="height: 80px;max-width: 120px;display: flex;align-items: center;">
          </template>
        </el-table-column>
        <el-table-column align="center" label="种类" prop="species"/>
        <el-table-column align="center" label="年龄" prop="age"/>
        <el-table-column align="center" label="描述" prop="description" width="300px"/>
        <el-table-column align="center" label="状态" prop="available">
          <template #default="scope">
            <el-tag v-if="scope.row.available === 0" type="warning">已领养</el-tag>
            <el-tag v-if="scope.row.available === 1">可领养</el-tag>
            <el-tag v-if="scope.row.available === 2" type="danger">不可领养</el-tag>
          </template>
        </el-table-column>
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

    <el-dialog v-model="insertDialogVisible" title="添加宠物信息" width="60%">
      <el-form ref="form" :model="insert" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="insert.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item label="种类">
          <el-input v-model="insert.species" placeholder="种类"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="insert.age" placeholder="年龄"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="insert.description" placeholder="描述"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="insert.available">
            <el-radio :label="0">已领养</el-radio>
            <el-radio :label="1">可领养</el-radio>
            <el-radio :label="2">不可领养</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="doInsertSubmit">提交</el-button>
        <el-button type="danger" @click="hidEditWin">取消</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="editDialogVisible" title="编辑宠物信息" width="60%">
      <el-form ref="form" :model="edit" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="edit.name" placeholder="名称" readonly></el-input>
        </el-form-item>
        <el-form-item label="种类">
          <el-input v-model="edit.species" placeholder="种类"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="edit.age" placeholder="年龄"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="edit.description" placeholder="描述"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="edit.available">
            <el-radio :label="0">已领养</el-radio>
            <el-radio :label="1">可领养</el-radio>
            <el-radio :label="2">不可领养</el-radio>
          </el-radio-group>
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
import {onMounted, ref, watch} from "vue";
import axios from "axios";
import {CirclePlus, Delete, Search} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";

const pets = ref([])

const doSearch = ref(false)

const searchTotal = ref(false)

const search = () => {
  doSearch.value = true;
  searchTotal.value = true;
}

const pagination = ref({
  page: 1,
  size: 5,
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
  axios.get(`http://localhost:8080/api/admin/pet?page=${page}&pageSize=${size}`)
      .then(response => {
        pets.value = response.data.data;
        total.value = response.data.total;
      })
      .catch(error => {
        console.error('请求错误!');
      });
};

onMounted(() => {
  fetchPets();
});

const insert = ref()

const insertDialogVisible = ref(false)

const doInsert = () => {
  insert.value = {};
  insertDialogVisible.value = true;
}

const doInsertSubmit = () => {
  axios.post('http://localhost:8080/api/admin/pet/insert', insert.value)
      .then(response => {
        fetchPets();
        ElMessage({
          type: 'success',
          message: '添加成功',
        })
        insertDialogVisible.value = false;
      })
      .catch(error => {
        ElMessage.error('请求失败，请联系管理员。')
      });
}

const query = ref({
  name: '',
  species: '',
  available: ''
})

const doQuery = () => {
  const {name, species, available} = query.value;
  const {page, size} = pagination.value;

  const params = {};
  if (name) {
    params.name = name;
  }
  if (species) {
    params.species = species;
  }
  if (available) {
    params.available = available;
  }

  axios.get(`http://localhost:8080/api/admin/pet/query?page=${page}&pageSize=${size}`, {params})
      .then(response => {
        pets.value = response.data.data;
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

const edit = ref()

const editDialogVisible = ref(false)

const doEdit = (name) => {
  edit.value = {};
  editDialogVisible.value = true;
  for (let i = 0; i < pets.value.length; i++) {
    if (pets.value[i].name === name) {
      edit.value = pets.value[i];
      break;
    }
  }
}

const doEditSubmit = () => {
  axios.post('http://localhost:8080/api/admin/pet/edit', edit.value)
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
        axios.post('http://localhost:8080/api/admin/pet/delete', {name: name})
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

const selectedData = ref()

const Select = (selection) => {
  selectedData.value = selection.map(item => item.name);
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
        axios.post('http://localhost:8080/api/admin/pet/deletes', selectedData.value)
            .then(response => {
              ElMessage({
                type: 'success',
                message: '删除成功',
              })
              fetchPets();
            })
            .catch(error => {
              ElMessage.error('选中存在已被领养的宠物，无法删除。')
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