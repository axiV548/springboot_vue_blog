<template>
    <div>
        <!--右关于我-->
        <div class="ui segment index-background">
          <div>
            <div class="m-bg-class_cover" align="center">
              <div class="m-margin-top-max" align="center">
                <img src="@/assets/image/IMG_4566.jpg" class="ui circular image" align="center" style="width: 80px;height: 80px;">
              </div>

              <div align="center">axiV548</div>
              <div class="" align="center">
                <a href="https://github.com/axiV548/" target="_blank" class="ui github circular icon button" data-content="点击跳转GitHub" data-position="bottom center"><i class="github icon"></i></a>
              </div>
            </div>
          </div>
        </div>

        <!--右分类-->
        <div class="ui secondary segment index-background">
          <div class="ui two column grid">
            <div class="column">
              <i class="th large icon"></i>分类
            </div>
            <div class="right aligned column">
              <a href="/types" target="_blank">更多<i class="angle double right icon"></i></a>
            </div>
          </div>
          <div class="ui fluid vertical menu index-background" v-for="type in types" v-if="!type.type_tag_level">
            <a :href="'/types?id=' + type.type_tag_id" target="_blank" class="item">
              <span>{{ type.type_tag_name }}</span>
              <div class="ui label">{{ type.count }}</div>

            </a>
          </div>
        </div>


        <!--右推荐-->

        <div class="ui attached secondary segment">
          <i class="bookmark icon"></i>最新推荐
        </div>
        <div class="ui attached segment index-background" v-for="recommend in blog_recommend">
          <a :href="'/blog?id=' + recommend.blog_id" target="_blank" class="index-text-color" >{{ recommend.blog_title }}</a>
        </div>
    </div>
</template>

<script>
  export default {
    name: "right_navigation",
    data() {
      return{
        types:[],
        blog_recommend:[],
        img_url: this.$base_url + '/firstImg/'
      }
    },
    created() {
      this.$http.get(this.$base_url + "/types").then((res)=>{
        this.types = res.data.data;
      });
      this.$http.get(this.$base_url + "/blogs/recommend").then((res)=>{
        this.blog_recommend = res.data.data;
      });
    }
  }
</script>

<style scoped>

</style>
