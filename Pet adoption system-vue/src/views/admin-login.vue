<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">管理员登录</h2>
      <el-form ref="loginForm" :model="form" :rules="rules" class="login-form" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item style="transform: translateX(18px)">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button @click="this.$router.push('/')">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {reactive} from 'vue';
import router from "@/router/index.js";

const form = reactive({
  username: '',
  password: '',
});

const rules = {
  username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
};

function login() {
  if (form.username === 'admin' || form.password === 'admin') {
    router.push('/admin');
  }
}

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
