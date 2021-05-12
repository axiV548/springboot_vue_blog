<template>
  <div>
    <nav class="ui attached index-navigation-mini index-background index-nav-shadow">
      <div class="ui container">
        <div class="ui secondary stackable menu">
          <h2 class="ui blue header item">DD</h2>
          <a href="/" class="m-item item index-mobile"><i class="home icon"></i>首页</a>
          <a href="/types" class="m-item item index-mobile"><i class="th icon"></i>分类</a>
          <a href="/albums" class="m-item item index-mobile"><i class="camera retro icon"></i>相册</a>
          <a href="/about" class="m-item item index-mobile" ><i class="info icon"></i>关于</a>
          <div class="m-item right item index-mobile">

            <div>
              <div class="ui icon input index-background" >
                <input type="text" placeholder="搜索" @keyup.enter="goToPage()" v-model="kw">
                <i class="search link icon" @click="goToPage()"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
    export default {
      name: "navigate",
      data() {
        return{
          kw: "",
        }
      },
      methods: {
        goToPage() {
          this.$router.push({name:'search', query: {"kw": this.kw}})
        }
      }
    }

</script>

<style scoped>

</style>
