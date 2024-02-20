<template>
  <el-container class="container">
    <el-header v-if="!Admin" style="--el-menu-bg-color: rgb(240,244,255);--el-header-padding: 0">

      <div style="display: flex;justify-content: space-between">
        <div class="nav" style="flex: 1">
          <el-menu mode="horizontal">
            <el-menu-item>
              <router-link style="display: flex" to="/">
                <img alt="logo" src="@\assets\pet-logo.png" style="height: 50px">
                <div class="logo" style="font-size: 20px">宠物领养</div>
              </router-link>
            </el-menu-item>
            <el-menu-item>
              <router-link to="/guide">
                <el-icon>
                  <Grid/>
                </el-icon>
                宠物图鉴
              </router-link>
            </el-menu-item>
          </el-menu>
        </div>

        <div class="user" style="transform: translateX(-10px)">
          <el-menu :ellipsis="false" mode="horizontal">
            <el-menu-item>
              <router-link to="/admin/login">
                <el-icon>
                  <Setting/>
                </el-icon>
                管理员登录
              </router-link>
            </el-menu-item>
          </el-menu>
        </div>
      </div>
    </el-header>

    <el-main :class="['el-main', { 'no-padding': Padding }]">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script setup>
import {ref, watch} from 'vue';
import {Grid, Setting} from '@element-plus/icons-vue'
import {useRoute} from "vue-router";

const route = useRoute();
let Admin = ref(false);
let Padding = ref(false);

// 进入后台时隐藏前台的头部导航栏
watch(route, (to, from) => {
  if (to.path.startsWith('/admin')) {
    Admin.value = true;
    Padding.value = true;
  } else {
    Admin.value = false;
    Padding.value = false;
  }
});


</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.no-padding {
  padding: 0 !important;
}

</style>
