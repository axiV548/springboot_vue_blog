<template>
  <nav class="ui inverted grey attached segment index-navigation-mini">
    <div class="ui container">
      <div class="ui inverted grey secondary stackable menu">
        <h2 class="ui teal header item">管理</h2>
        <a href="/admin" class="nav-item item index-mobile" ><i class="home icon"></i>文章</a>
        <a href="/admin/types" class="nav-item item index-mobile" ><i class="th large icon"></i>分类</a>
        <div class="right nav-item index-mobile menu">
          <div class="ui dropdown item">
            <div class="text">
              <img src="https://cache.cswsadlab.com/wp-content/uploads/2015/03/笑颜1-150x150.jpg" alt="" class="ui avatar image">DD
            </div>
            <a @click="signOut()" class="item">注销</a>
          </div>
        </div>
      </div>

    </div>
  </nav>
</template>

<script>
    export default {
      name: "admin_navigate",
      methods: {
        signOut() {
          if (confirm('确定要注销吗?') == true) {
            localStorage.removeItem('token')
            this.$router.go(0);
          }
        }
      },
      created() {

      }

    }
</script>

<style scoped>

</style>
