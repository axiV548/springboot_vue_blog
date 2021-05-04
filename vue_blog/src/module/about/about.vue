<template>
  <div>
    <navigate></navigate>
    <div class="blog-container index-footer-big">
      <div class="ui container">
        <div class="ui stackable grid">
          <!--左列表-->
          <div class="eleven wide column">
            <!--头-->
            <div class="ui top attached segment">
              <div class="ui horizontal divider" style="font-size: 2em;">关于博客</div>
              <div class="about_txt"  style="">
                <p>欢迎来到我的博客，在这里我会发布一些平时学习到的知识、技巧和遇到的问题及解决的方法，同时也会记录一些个人的生活。</p>
                <p>如果本博客能对您有任何帮助，我将十分荣幸，有任何问题或者建议也欢迎发送邮件到"axiV548@outlook.com"与我一起讨论。</p>
              </div>
            </div>
          </div>
          <!--右导航-->
          <div class="five wide column">
            <right_navigate></right_navigate>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import navigate from "../../components/navigate";
  import right_navigate from "../../components/right_navigate";
  export default {
    name: "about",
    components:{
      navigate,
      right_navigate,
    },
  }
</script>

<style scoped>

.about_txt{
  line-height: 2em;
  text-indent: 2em;
  font-style: normal;
  font-size: 1.25em;
  margin: 15px 50px 50px 50px;
}
</style>
