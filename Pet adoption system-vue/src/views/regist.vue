<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">用户注册</h2>
      <el-form ref="loginForm" :model="form" :rules="rules" class="login-form" label-width="100px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="username">
          <el-input v-model="form.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item style="transform: translateX(18px)">
          <el-button type="primary" @click="regist">注册</el-button>
          <el-button @click="">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";


const form = ref({
  username: '',
  password: '',
})

const rules = {
  name: [{required: true, message: '请输入名称', trigger: 'blur'}],
  username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
  email: [{required: true, type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change']}]
};


const regist = async () => {
  axios.post('http://localhost:8080/api/regist', form.value)
      .then(response => {
        ElMessage({
          message: '注册成功',
          type: 'success',
        })
        router.push('/user/login')
      })
      .catch(error => {
        ElMessage({
          message: '系统错误，请联系管理员',
          type: 'warning',
        })
      });
};

</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
}

.login-card {
  width: 400px;
  padding: 20px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  margin-top: 20px;
}
</style>