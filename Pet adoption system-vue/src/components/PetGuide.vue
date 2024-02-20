<template>
  <div>
    <div class="slogan">
      <img alt="" src="@/assets/slogan.jpg" style="position: relative;width: 70%;border-radius: 10px;left: 15px">
      <div style="position: relative;left: 11%;font-family: Stroke-body, sans-serif;">
        <span style="transform: translateY(20%)">关爱流浪动物</span>
        <span style="transform: translateY(53%)">领养代替购买</span>
      </div>
    </div>

    <!--  宠物列表  -->
    <div class="card-container" @click="dialog">
      <div v-for="pet in pets" :key="pet.id" class="card">
        <h2>{{ pet.name }}</h2>
        <div style="height: 160px;">
          <img :src="`http://localhost:8080/pet/${pet.name}.jpg`" alt="">
        </div>
        <p><span>种类:</span><span>{{ pet.species }}</span></p>
        <p><span>年龄:</span><span>{{ pet.age }}岁</span></p>
        <p><span>描述:</span><span>{{ pet.description }}</span></p>
        <p><span>领养:</span><span>{{ pet.available ? '可领养' : '暂不可领养' }}</span></p>
      </div>
    </div>

    <!--  领养须知，5秒倒计时  -->
    <el-dialog v-model="dialogVisible" style="height: 390px" title="领养须知" width="45%">
      <h3 style="text-align: center">感谢您考虑领养宠物！在领养之前，请阅读以下须知：</h3>
      <ul style="margin-bottom: 16px;">
        <li>领养宠物需要一定的责任心和时间投入。</li>
        <li>确保您有足够的经济能力来照顾宠物的食物、医疗和其他费用。</li>
        <li>了解宠物的品种特点和需求，确保您能提供适当的环境和照顾。</li>
        <li>请确保您的家庭成员都同意领养宠物，并且没有对宠物过敏的情况。</li>
        <li>在领养之前，与宠物的前主人或领养机构进行充分的沟通和了解。</li>
      </ul>
      <p style="text-align: center">如果您已经阅读并理解了以上须知，并且准备好领养宠物，请继续填写领养申请表格。</p>
      <template #footer>
        <router-link class="button-wrapper" style="display: flex;justify-content: center;" to="/adopt">
          <el-button :disabled="disableButton" plain type="danger">填写领养申请表格</el-button>
          <el-text v-if="CountDown" class="mx-1" type="danger">({{ countdown }}秒后可点击)</el-text>
        </router-link>
      </template>
    </el-dialog>

    <!--  回到顶部按钮  -->
    <el-backtop :bottom="50" :right="50" style="width: 50px;height: 50px;">
      <span style="width: 45px;height: 45px;font-size: 16px;transform: translate(6px,1px)">回到顶部</span>
    </el-backtop>
  </div>
</template>

<script setup>
import axios from 'axios';
import {onMounted, ref} from "vue";

const pets = ref([]);
const dialogVisible = ref(false)

onMounted(() => {
  axios.get('http://localhost:8080/api/pet')
      .then(response => {
        pets.value = response.data;
      })
      .catch(error => {
        console.error('请求错误!');
      });
});

const disableButton = ref(true);
const CountDown = ref(true);
const countdown = ref(5);

const dialog = () => {
  dialogVisible.value = true;
  const timer = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--;
    } else {
      disableButton.value = false;
      CountDown.value = false;
      clearInterval(timer);
    }
  }, 1000);
}

</script>

<style lang="scss" scoped>
.slogan {
  position: relative;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 10px;
  width: 80%;
  left: 50%;
  transform: translateX(-50%);
  display: flex;

  span {
    writing-mode: vertical-rl;
    font-size: 3.5vw;
  }
}

.card-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  grid-gap: 20px;
  padding-top: 10px;

  .card {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;

    img {
      position: relative;
      width: 233px;
      max-height: 170px;
      height: auto;
      left: 50%;
      transform: translateX(-50%);
      border-radius: 5px;
    }

    span:first-child {
      padding-left: 15px;
      padding-right: 10px;
    }
  }
}

.button-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 16px;
}

</style>