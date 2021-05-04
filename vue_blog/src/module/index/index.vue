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
              <div class="ui middle aligned two column grid">
                <div class="column">
                  <h3 class="ui blue header">文章</h3>
                </div>
              </div>
            </div>

            <!--中-->
            <div class="ui attached segment">
              <div class="ui padded vertical segment">
                <div class="ui middle aligned mobile reversed stackable grid" v-for="blog in blogs">
                  <div class="five wide column">
                    <a :href="'/blog?id=' + blog.blog_id" target="_blank">
                      <img :src="img_url+blog.blog_first_img" alt="" class="ui rounded image">
                    </a>
                  </div>
                  <div class="eleven wide column">
                    <h3 class="ui header"><a :href="'/blog?id=' + blog.blog_id" target="_blank" style="color: #333 !important;" >{{ blog.blog_title }}</a></h3>
                    <p class="index-text" >{{ blog.blog_description }}</p>
                    <div class="ui grid">
                      <div class="eleven wide column">
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

                      <div class="five wide column">
                        <p target="_blank" v-for="tag in blog.tags" class="ui blue basic label mini">{{ tag.type_tag_name}}</p>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>

            <!--底-->

            <div class="ui bottom attached segment">
              <div class="ui middle aligned two column grid">
                <div class="column">
                  <p v-if="page > 1" @click="previousPage()" class="ui mini blue basic button">上一页</p>
                </div>
                <div class="right aligned column">
                  <p v-if="page < size/8" @click="nextPage()" class="ui mini blue basic button">下一页</p>
                </div>
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
      name: "index",
      data() {
        return{
          blogs:[],
          page: 1,
          size: 0,
          img_url: this.$base_url + '/firstImg/'
        }
      },
      methods: {
        getBlogs(index) {
          this.page = index || this.page
          // console.log(this.page)
          this.$http.get(this.$base_url + "/blogs/page/" + this.page).then((res)=>{
            this.blogs = res.data.data;
            this.size = res.data.other;
            // console.log("blogs", this.blogs);
          });
        },

        nextPage() {
          this.page++;
          this.getBlogs(this.page)
        },

        previousPage() {
          this.page--;
          this.getBlogs(this.page)
        }

      },
      components:{
        navigate,
        right_navigate,
      },
      created() {
        this.getBlogs();
      }
    }
</script>

<style scoped>

</style>
