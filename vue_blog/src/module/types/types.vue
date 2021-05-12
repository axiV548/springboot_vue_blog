<template>
  <div>
    <navigate></navigate>
    <div>
      <div class="ui container">
        <!--header-->
        <br><br>
        <div class="ui top attached segment">
          <div class="ui middle aligned two column grid">
            <div class="column">
              <h3 class="ui blue header">分类</h3>
            </div>
          </div>
        </div>

        <div class="ui attached segment">
          <div class="ui labeled button" style="padding-bottom: 0.5em !important;" v-for="type in types">
            <a @click="getTypeBlogs(tag_blog_id=type.type_tag_id)" class="ui basic button">{{ type.type_tag_name }}</a>
            <div class="ui basic left pointing label">{{ type.count }}</div>
          </div>
        </div>

        <div class="ui top attached blue segment" style="padding-left: 2.5em !important;">
          <div class="ui padded vertical segment ">
            <div class="ui middle aligned mobile reversed stackable grid" >
              <div class="eleven wide column" v-for="blog in blogs">
                <div class="ui grid">
                  <div class="five wide column">
                    <a :href="'/blog?id=' + blog.blog_id" target="_blank">
                      <img :src="img_url+blog.blog_first_img" alt="" class="ui rounded image">
                    </a>
                  </div>
                  <div class="eleven wide column">
                    <h3 class="ui header" ><a :href="'/blog?id=' + blog.blog_id" target="_blank" class="m-black">{{ blog.blog_title }}</a></h3>
                    <p class="m-text index-text">{{ blog.blog_description }}</p>
                    <div class="ui mini horizontal link list">
                      <div class="item">

                        <img src="@/assets/image/IMG_4566.jpg" alt="" class="ui avatar image">
                        <div class="content">axiv548</div>&nbsp&nbsp&nbsp&nbsp
                        <i class="eye icon"></i>&nbsp
                        <span>{{ blog.blog_views }}</span>
                      </div>&nbsp
                      <div class="item">
                        <i class="calendar icon"></i>
                        <span>{{ blog.blog_update_time }}</span>
                      </div>
                    </div>
                  </div>
                  <div class="right aligned five wide column">
                  </div>
                </div>
              </div>

              <div class="five wide column">
                <a href="#" target="_blank">
                  <img alt="" class="ui rounded image">
                </a>
              </div>

            </div>
          </div>
        </div>

        <!--footer-->
<!--        <div class="ui bottom attached segment">
          <div class="ui middle aligned two column grid">
            <div class="column">
              <a href="#" class="ui mini blue basic button">上一页</a>
            </div>
            <div class="right aligned column">
              <a href="#" class="ui mini blue basic button">下一页</a>
            </div>
          </div>
        </div>-->

      </div>
    </div>
  </div>

</template>

<script>
  import navigate from "../../components/navigate";
    export default {
      name: "types",
      components:{
        navigate,
      },
      data() {
        return{
          tag_blog_id: "",
          types:[],
          blogs:[],
          img_url: this.$base_url + '/firstImg/'
        }
      },
      methods: {
        getTypes() {
          this.$http.get(this.$base_url + "/types").then((res)=>{
            this.types = res.data.data;
          });
        },
        // 根据分类查询文章
        getTypeBlogs() {
          // console.log(this.tag_blog_id);
          this.$http.get(this.$base_url + "/types/tags/" + this.tag_blog_id).then((res)=>{
          this.blogs = res.data.data;

          });
        },
        //首页分类更多跳转
        getMoreTypes() {
          // console.log(this.tag_blog_id);
          if (this.$route.query.id == null) {
            this.$route.query.id = 1
          }
          this.$http.get(this.$base_url + "/types/tags/" + this.$route.query.id).then((res)=>{
            this.blogs = res.data.data;
            console.log(this.blogs)
          });
        }
      },
      created() {
        this.getTypes();
        this.getMoreTypes();
      }
    }
</script>

<style scoped>

</style>
