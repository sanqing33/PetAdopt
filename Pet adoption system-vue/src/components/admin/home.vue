<template>
  <div class="welcome-page">
    <header class="welcome-header">
      <h1 style="font-size: 30px">欢迎来到宠物领养后台管理系统</h1>
    </header>
    <main class="welcome-content">
      <section class="intro-section">
        <h2>关于我们</h2>
        <p>我们致力于提供一个方便、安全和可靠的宠物领养平台。通过我们的后台管理系统，您可以轻松管理宠物信息、用户信息和领养情况。</p>
      </section>
      <section class="features-section">
        <h2>特色功能</h2>
        <div class="feature-card">
          <h3>宠物管理：</h3>
          <p>方便管理宠物的基本信息，包括品种、年龄、性别等。</p>
        </div>
        <div class="feature-card">
          <h3>用户管理：</h3>
          <p>管理用户信息和领养情况，确保良好的用户体验。</p>
        </div>
        <div class="feature-card">
          <h3>领养情况：</h3>
          <p>跟踪宠物领养情况，及时更新领养状态和相关信息。</p>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>

</script>

<style>
.welcome-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.welcome-header {
  text-align: center;
  margin-bottom: 40px;
}

.welcome-content {
  margin-bottom: 40px;
}

.intro-section,
.features-section,
.feedback-section {
  margin-bottom: 40px;
}

.feature-card {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

h2 {
  transform: translateX(-20px);
}

</style>
