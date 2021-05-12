<template>
  <div>
    <navigate></navigate>
    <div class="blog-container index-footer-big index-text">
      <div class="ui container">
        <!--        头部-->
        <div class="ui top attached segment">
          <div class="ui grid">
            <div class="eleven wide column">
              <div class="ui horizontal link list" style="text-align: center !important;">
                <div class="item">
                  <img src="@/assets/image/IMG_4566.jpg" alt="" class="ui avatar image">
                  <div class="content">axiV548</div>
                  <i class="calender icon"></i><span>{{ blog.blog_update_time }}</span>
                </div>
                <div class="item">
                  <i class="eye icon"></i><span>{{ blog.blog_views }}</span>
                </div>
              </div>
            </div>
            <div class="five wide column">
              <p target="_blank" v-for="tag in blog.types" class="ui blue basic label left">{{ tag.type_tag_name }}</p>
            </div>
          </div>
        </div>
        <div class="ui attached padded segment">

          <!--            内容-->
          <h2 class="ui center aligned header">{{ blog.blog_title }}</h2>
<!--          <div id="content" class="blog-padded typo typo-selection">{{ blog.blog_content }}</div>-->
          <mavon-editor
            :value="blog.blog_content"
            defaultOpen="preview"
            :boxShadow="false"
            :editable="false"
            :subfield="false"
            :toolbarsFlag="false"
            :aligncenter="true"
          ></mavon-editor>
        </div>

        <div class="ui attached positive message">
          <div class="ui middle aligned grid">
            <div class="eleven wide column">
              <ul class="list">
                <li>作者：<span>axiV548</span></li>
                <li>发布时间：<span>{{ blog.blog_update_time }}</span></li>
                <li>版权声明：自由转载-非商用-非衍生-保持署名</li>
                <li>转载声明：转载请保留原文链接，禁止商用转载</li>
              </ul>
            </div>
          </div>
        </div>


        <div class="ui bottom attached segment">
          <!--            留言-->
          <div id="comment-container" class="ui blue comments" style="max-width: 100%">
            <div>
              <div class="ui threaded comments">
                <h3 class="ui dividing header">留言</h3>
                <div class="comment" v-for="comment in blog_comment">
                  <a class="avatar">
                    <img src="@/assets/image/IMG_1970.jpg" v-if="!comment.comment_admin">
                    <img src="@/assets/image/IMG_4566.jpg" v-if="comment.comment_admin">
                  </a>
                  <div class="content">
                    <a class="author">
                      <span>{{ comment.comment_nickname }}
                          <div class="ui mini basic blue left pointing label" v-if="comment.comment_admin" style="padding: 0.2em !important;" >博主</div>
                      </span>
                    </a>
                    <div class="metadata">
                      <span class="date">{{ comment.comment_create_time }}</span>
                    </div>
                    <div class="text">{{ comment.comment_content }}</div>
                  </div>

                </div>
              </div>
            </div>
          </div>

          <div id="comment-form" class="ui form">
            <input type="hidden" name="blog.id">
            <input type="hidden" name="parentComment.id" value="-1">
            <div class="field">
              <textarea v-model="comment.comment_content" placeholder="请输入信息"></textarea>
            </div>
            <div class="fields">
              <div class="field">
                <div class="ui left icon input">
                  <i class="user icon"></i>
                  <input type="text" v-model="comment.comment_nickname" placeholder="名称">
                </div>
              </div>
              <div class="field">
                <div class="ui left icon input">
                  <i class="mail icon"></i>
                  <input type="text" v-model="comment.comment_email" placeholder="邮箱">
                </div>
              </div>
              <div class="field">
                <button type="button" @click="postComment" class="ui blue button"><i class="edit icon"></i></button>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
<!--  <div class="index-positon" style="padding: 3em !important;">-->
<!--    <div class="ui vertical icon buttons">-->
<!--      <a href="#" class="ui blue button"><i class="chevron up icon"></i></a>-->
<!--      <a href="#comment-container" class="ui blue button"><i class="chevron down icon"></i></a>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
  import navigate from "../../components/navigate";
    export default {
      name: "blog",
      components:{
        navigate,
      },
      data() {
        return {
          blog:{},
          blog_comment:{},
          comment:{}
        }

      },
      methods: {
        findBlog() {
          this.$http.get(this.$base_url + "/blogs/" + this.$route.query.id).then(res=>{
            this.blog = res.data.data
            this.findComment();

          })
        },
        findComment() {
          this.$http.get(this.$base_url + "/comment/" + this.$route.query.id).then(res=>{
            this.blog_comment = res.data.data

          })
        },

        postComment() {
          this.comment.blog_id = this.$route.query.id

          if (this.comment && this.comment.blog_id && this.comment.comment_content) {
            if (!this.comment.comment_nickname) {
              this.comment.comment_nickname = "XXX"
            }
            this.$http.post(this.$base_url + "/comment/", this.comment).then(res=>{
              this.$router.go(0);

            })
          } else {
            confirm('内容不能为空！！！')
          }
        },
      },
      created() {

        this.findBlog();
      }
    }
</script>

<style scoped>

</style>
