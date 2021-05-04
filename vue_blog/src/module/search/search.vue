<template>
  <div>
    <navigate></navigate>
    <div  class="m-container-small m-padded-tb-big">
      <div class="ui container">

        <!--header-->
        <br><br>
        <div class="ui top attached segment">
          <div class="ui top attached segment">
            <div class="ui middle aligned two column grid">
              <div class="column">
                <h3 class="ui blue header">关键字：{{ kw }}</h3>
              </div>
            </div>
          </div>


          <!--中-->
          <div class="ui attached segment">
            <div class="ui padded vertical segment">
              <div class="ui middle aligned mobile reversed stackable grid" v-for="blog in blogs">
                <div class="five wide column">
                  <a :href="'/blog?id=' + blog.blog_id"  target="_blank">
                    <img :src="img_url+blog.blog_first_img" width="200px" class="ui rounded image">
                  </a>
                </div>
                <div class="eleven wide column">
                  <h3 class="ui header"><a :href="'/blog?id=' + blog.blog_id" target="_blank" style="color: #333 !important;">{{ blog.blog_title }}</a></h3>
                  <p class="index-text" >{{ blog.blog_description }}</p>
                  <div class="ui grid">
                    <div class="eleven wide column">
                      <div class="ui mini horizontal link list">
                        <div class="item">
                          <img src="@/assets/image/IMG_4566.jpg" alt="" class="ui avatar image">
                          <div class="content"></div>
                          <i class="calender icon"></i><span>{{ blog.blog_update_time }}</span>
                        </div>
                        <div class="item">
                          <i class="eye icon"></i><span>{{ blog.blog_views }}</span>
                        </div>
                      </div>
                    </div>
                    <div class="five wide column">
                      <p v-for="tag in blog.tags" target="_blank" class="ui blue basic label">{{ tag.type_tag_name }}</p>
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
                <p  v-if="page < size/8" @click="nextPage()" class="ui mini blue basic button">下一页</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import navigate from "../../components/navigate";
    export default {
      name: "search",
      components:{
          navigate,
      },
      data () {
        return {
          blogs: [],
          kw: "",
          page: 1,
          size: 0,
          img_url: this.$base_url + '/firstImg/'
        }
      },
      methods: {
        getSearch() {
          this.kw = this.$route.query.kw
          this.$http.get(this.$base_url + "/search/" + this.$route.query.kw + "/" + this.page).then((res)=>{
          this.blogs = res.data.data;
          this.size = res.data.other;
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
      created() {
        this.getSearch();
      },
      watch: {
        $route(to, from) {
          this.$router.go(0)
        }
      }

    }
</script>

<style scoped>

</style>
