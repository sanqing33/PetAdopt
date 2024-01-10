<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">用户登录</h2>
      <el-form ref="loginForm" :model="form" :rules="rules" class="login-form" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item style="transform: translateX(-18px)">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="warning" @click="this.$router.push('/user/regist')">注册</el-button>
          <el-button @click="this.$router.push('/')">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const form = ref({
  username: '',
  password: '',
});

const rules = {
  username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
};

const login = async () => {
  axios.post('http://localhost:8080/api/login', form.value)
      .then(response => {
        const result = response.data; // 获取响应数据
        if (result === '登录成功') {
          ElMessage({
            message: '登录成功',
            type: 'success',
          })
          router.push('/guide')
        } else {
          ElMessage({
            message: '用户名或密码错误',
            type: 'warning',
          })
        }
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
