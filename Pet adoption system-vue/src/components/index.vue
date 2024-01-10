<template>
  <div>
    <span id="title">欢迎来到宠物领养站</span>

    <div class="index">
      <el-carousel height="480px" style="top: 10px;" trigger="click" type="card">
        <el-carousel-item>
          <img alt="" src="@/assets/pets/cat1.jpg" style="max-height: 100%;">
        </el-carousel-item>
        <el-carousel-item>
          <img alt="" src="@/assets/pets/dog1.jpg" style="max-height: 100%;">
        </el-carousel-item>
        <el-carousel-item>
          <img alt="" src="@/assets/pets/cat2.jpg" style="max-height: 100%;">
        </el-carousel-item>
        <el-carousel-item>
          <img alt="" src="@/assets/pets/dog2.jpg" style="max-height: 100%;">
        </el-carousel-item>
        <el-carousel-item>
          <img alt="" src="@/assets/pets/cat3.jpg" style="max-height: 100%;">
        </el-carousel-item>
        <el-carousel-item>
          <img alt="" src="@/assets/pets/dog3.jpg" style="max-height: 100%;">
        </el-carousel-item>

      </el-carousel>
    </div>

    <div class="href">
      <router-link style="transform: translateX(-80px)" to="/guide">
        <img alt="" src="@/assets/friends.jpg">
        <span>看看我的伙伴</span>
      </router-link>
      <router-link style="transform: translateX(80px)" to="/user/login">
        <img alt="" src="@/assets/adopt.jpg">
        <span>登录/注册<br>&ensp;带我回家</span>
      </router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.index {
  position: relative;
  width: 90%;
  left: 50%;
  transform: translateX(-50%);
  overflow: hidden;
}

#title {
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
  font-family: Stroke-body, sans-serif;
  font-size: 70px;
  font-weight: bold;
  background: #f1f1f1;
  padding: 5px;
  background: url("@/assets/slogan.jpg") 50% 50%;
  background-size: cover;
  color: transparent;
  background-clip: text;
}

el-carousel img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.href {
  display: flex;
  justify-content: center;
  transform: translateY(45px);
}

.href span {
  position: relative;
  font-size: 32px;
  color: black;
  font-family: Stroke-body, sans-serif;
  transform: translateX(-50%);
}

.href img {
  position: relative;
  height: 90px;
  display: block;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.65);
  left: 50%;
  transform: translateX(-50%);
}

</style>
