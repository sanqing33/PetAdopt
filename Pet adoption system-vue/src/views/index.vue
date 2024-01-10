<template>
  <div class="container">
    <el-container>
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
          <div class="user">
            <el-menu :ellipsis="false" mode="horizontal">
              <el-menu-item>
                <el-dropdown>
                <span class="el-dropdown-link" style="outline: none;">
                  <el-icon><User/></el-icon>
                  {{ username }}
                  <el-icon class="el-icon--right"><arrow-down/></el-icon>
                </span>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <router-link to="/user/login">
                        <el-dropdown-item>登录</el-dropdown-item>
                      </router-link>
                      <router-link to="/user/regist">
                        <el-dropdown-item>注册</el-dropdown-item>
                      </router-link>
                      <router-link to="/user">
                        <el-dropdown-item>个人资料</el-dropdown-item>
                      </router-link>
                      <router-link to="/admin/login">
                        <el-dropdown-item divided>管理员登录</el-dropdown-item>
                      </router-link>
                      <router-link to="">
                        <el-dropdown-item divided>退出登录</el-dropdown-item>
                      </router-link>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </el-menu-item>
            </el-menu>
          </div>
        </div>
      </el-header>

      <el-main :class="['el-main', { 'no-padding': Padding }]">
        <router-view></router-view>
      </el-main>
    </el-container>

  </div>
</template>

<script setup>
import {ref, watch} from 'vue';
import {ArrowDown, Grid, User} from '@element-plus/icons-vue'
import {useRoute} from "vue-router";

const username = '用户中心'

const route = useRoute();
let Admin = ref(false);
let Padding = ref(false);

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
