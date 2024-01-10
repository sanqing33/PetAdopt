<template>
  <div>
    <el-container>
      <el-aside style="height: 100vh;border: 1px solid #ccc;background-color: white" width="200px">
        <el-row class="tac">
          <el-col :span="24">
            <h4 style="display: flex;align-items: center;justify-content: center;text-align: center">
              <img alt="logo" src="@\assets\pet-logo.png" style="height: 50px">
              宠物领养<br>后台管理系统
            </h4>
            <el-menu :default-openeds="['2', '3']" class="el-menu-vertical-demo" default-active="1">
              <el-menu-item index="1" route="/admin">
                <el-icon>
                  <House/>
                </el-icon>
                <router-link to="/admin">首页</router-link>
              </el-menu-item>

              <el-menu-item index="/admin/pet">
                <el-icon>
                  <Menu/>
                </el-icon>
                <router-link to="/admin/pet">宠物信息</router-link>
              </el-menu-item>

              <el-menu-item index="/admin/user">
                <el-icon>
                  <User/>
                </el-icon>
                <router-link to="/admin/user">用户信息</router-link>
              </el-menu-item>

              <el-menu-item index="/admin/adopt">
                <el-icon>
                  <Tickets/>
                </el-icon>
                <router-link to="/admin/adopt">领养申请</router-link>
              </el-menu-item>

              <el-menu-item index="/admin/adoption">
                <el-icon>
                  <Connection/>
                </el-icon>
                <router-link to="/admin/adoption">领养情况</router-link>
              </el-menu-item>

              <el-menu-item index="5">
                <el-icon>
                  <RefreshLeft/>
                </el-icon>
                <router-link to="/">返回前台</router-link>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import {Menu, House, RefreshLeft, User, Connection, Tickets} from '@element-plus/icons-vue'

</script>

<style scoped>
a {
  color: black;
}

</style>