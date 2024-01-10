<template>
  <div>
    <div class="adoption-form">
      <h1 style="text-align: center">宠物领养申请</h1>
      <el-form ref="form" :model="form" label-width="120px" style="transform: translateX(-30px)">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" @input="change"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="form.age" :max="999" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" @input="change"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" @input="change"></el-input>
        </el-form-item>
        <el-form-item label="宠物类型" prop="petType">
          <input v-model="form.petType" name="petType" type="radio" value="猫"><label for="cat">猫</label>
          <input v-model="form.petType" name="petType" type="radio" value="狗"><label for="dog">狗</label>
          <input v-model="form.petType" name="petType" type="radio" value="其他"><label for="other">其他</label>
        </el-form-item>
        <el-form-item label="申请理由" prop="reason">
          <el-input v-model="form.reason" type="textarea" @input="change"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交申请</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="z-index: 1">
      <img alt="" src="@/assets/adopt-dog.jpg" style="bottom: 5%;left: 3%">
      <img alt="" src="@/assets/adopt-cat.png" style="bottom: 5%;right: 3%">
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router/index.js";

const form = ref({
  name: '',
  age: '',
  address: '',
  phone: '',
  petType: '',
  reason: '',
})

const change = () => {
  this.$forceUpdate();
}

const submitForm = () => {
  if (!form.value.name || !/^[\u4E00-\u9FA5A-Za-z\s]+$/.test(form.value.name)) {
    ElMessage.error('请输入有效的姓名！');
    return;
  }

  if (form.value.age < 18) {
    ElMessage.error('未满18岁！');
    return;
  }

  if (!form.value.address || !/^[\u4E00-\u9FA5A-Za-z0-9\s]+$/.test(form.value.address)) {
    ElMessage.error('请输入有效的地址！');
    return;
  }

  if (!form.value.phone || !/^\d{11}$/.test(form.value.phone)) {
    ElMessage.error('请输入有效的联系电话！');
    return;
  }

  if (!form.value.petType) {
    ElMessage.error('请选择宠物类型！');
    return;
  }

  if (!form.value.reason) {
    ElMessage.error('请输入有效的申请理由！');
    return;
  }

  axios.post('http://localhost:8080/api/adopt', form.value)
      .then(response => {
        ElMessageBox.alert('申请成功，请等待管理员审核。', '成功', {
          confirmButtonText: '确认',
          callback: () => {
            router.push("/guide");
          },
        })
      })
      .catch(error => {
        ElMessage.error('请求失败，请联系管理员。')
      });
}

</script>

<style scoped>
.adoption-form {
  position: absolute;
  top: 50%;
  left: 50%;
  max-width: 400px;
  padding: 20px;
  transform: translate(-50%, -50%);
  z-index: 2;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

img {
  position: absolute;
  height: 200px;
}

.radio-group input[type="radio"] {
  margin-right: 5px;
}

.radio-group label {
  margin-right: 10px;
}

</style>
