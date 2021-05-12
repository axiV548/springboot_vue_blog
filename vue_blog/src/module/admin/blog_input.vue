<template>
  <div>
    <admin_navigate></admin_navigate>

    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a @click="reload" class="item">新增</a>
          <a href="/admin" class="active item">管理</a>
        </div>
      </div>
    </div>


    <!--主页-->
    <div class="index-footer-big">
      <div class="ui container">
        <div class="ui">

          <div class="required field">
            <div class="ui left labeled input input_title">
              <input type="text" v-model="blog.blog_title" placeholder="标题">
            </div>
          </div>

          <div class="ui two fields input_tag">

            <div class="required field">
              <div class="ui left labeled action input">
                <label class="ui compact teal basic label">分类</label>
                <select class="ui search dropdown" v-model="blog_type" @change="newType()">
                  <option v-for="type in types" :value="type.type_tag_id" v-if="!type.type_tag_level">{{ type.type_tag_name }}</option>
                </select>
              </div>
            </div>

            <div class="field">
              <div class="ui left labeled action input tag_right">
                <label class="ui compact teal basic label">标签</label>
                <select class="ui fluid search dropdown" v-model="tags" multiple>
                  <option v-for="type in types" :value="type.type_tag_id" v-if="type.type_parent_id==blog_type">{{ type.type_tag_name }}</option>
                </select>
              </div>
            </div>

          </div>

          <div class="required field">
            <textarea class="input_describe" placeholder="博客描述" maxlength="200" v-model="blog.blog_description"></textarea>
          </div>

          <div class="required field">
            <mavon-editor defaultOpen="preview" v-model="blog.blog_content" ref="content_img" @imgAdd="addImg" @imgDel="delImg"></mavon-editor>
            <br>
          </div>

          <div class="required field ">
            <div class="ui left labeled input home_picture" style="width: 30%">
              <label class="ui teal basic label ">首页图</label>
              <input type="file" ref="first_img">
              <img :src="service_url+ 'firstImg/' +blog.blog_first_img" style="height: 50px" v-if="blog.blog_first_img" class="ui rounded image">

            </div>
          </div>

          <div class="inline fields option">
            <div class="field gap">
              <div class="ui checkbox">
                <input type="checkbox" id="recommendStatus" v-model="blog.blog_recommend" class="hidden">
                <label for="recommendStatus">推荐</label>
              </div>
            </div>
            <div class="field">
              <div class="ui checkbox gap">
                <input type="checkbox" id="commentStatus" v-model="blog.blog_comment" class="hidden">
                <label for="commentStatus">评论</label>
              </div>
            </div>
            <div class="field">
              <div class="ui checkbox gap">
                <input type="checkbox" id="publicStatus" v-model="blog.blog_public" class="hidden">
                <label for="publicStatus">公开</label>
              </div>
            </div>
          </div>


          <div class="ui right aligned container">
            <button type="button" @click="tabCheck(1)" class="ui teal button">发布</button>
            <button type="button" @click="tabCheck(0)" class="ui secondary button">保存</button>
            <button type="button" @click="goToPath()" class="ui button">返回</button>

          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
    import admin_navigate from "../../components/admin_navigate";
    export default {
      name: "blog_input",
      components:{
        admin_navigate,
      },

      data() {
        return {
          blog:{
            blog_recommend:0,
            blog_comment:0,
            blog_public:0
          },
          imgs: {},
          img_name: "",
          types: [],
          tags: [],
          blog_type:"",
          service_url: this.$base_url
        }
      },
      methods: {
        getBlog() {
          // 获取文章信息
          this.$http.get(this.$base_url + "/blogs/admin/" + this.$route.query.id).then((res) => {
            this.blog = res.data.data;
            for(var i = 0; i < this.blog.type_tags.length; i++) {
              if (this.blog.type_tags[i]['type_tag_level'] == 1) {
                this.tags.push(this.blog.type_tags[i]['type_tag_id'])
              } else {
                this.blog_type = this.blog.type_tags[i]['type_tag_id']
              };
            };
          });
        },

        getTypes() {
          // 获取所有分类
          this.$http.get(this.$base_url + "/types/admin").then((res) => {
            this.types = res.data.data;

          });
        },

        saveBlog(release) {
          this.blog.blog_release = release;
          this.blog.tags = this.tags;
          this.blog.tags.push(this.blog_type)

          let formData = new FormData;
          for( let key in this.blog ){
            //遍历对象属性
            formData.append(key, this.blog[key]);
          }
          formData.append("first_img", this.$refs.first_img.files[0]);
          this.$http({
            method: "post",
            url: this.$base_url + "/blogs/admin",
            data: formData,
          }).then((res)=>{
            if (res.data.code == 200) {
              this.goToPath();
            }
          });

        },

        putBlog(release) {
          this.blog.blog_release = release;
          this.blog.tags = this.tags;
          this.blog.tags.push(this.blog_type)
          delete this.blog.blog_update_time

          let formData = new FormData;
          for( let key in this.blog ){
            //遍历对象属性
            formData.append(key, this.blog[key]);
          }
          formData.append("first_img", this.$refs.first_img.files[0]);
          this.$http({
            method: "put",
            url: this.$base_url + "/blogs/admin/" + this.$route.query.id,
            data: formData,
          }).then((res)=>{
            if (res.data.code == 200) {
              this.goToPath();
            }
          });
        },

        addImg(pos, file) {

          let formData=new FormData();     //新建一个表单数据,用于提交文件
          formData.append("content_img", file);     //添加文件,参数分别是表单参数的名字和值.
          this.$http({
            method: "post",
            url: this.$base_url + "/blogs/admin/img",
            data: formData,
          }).then((res)=>{

            this.img_name = res.data.other;
            let img_url = this.service_url + '/contentImg/' + this.img_name;
            this.$refs.content_img.$img2Url(pos, img_url);     //将原来的图片url替换成data.url,注意$img2Url是自带的函数.
          });
          this.imgs[pos] = file;     //记录图片.
        },

        delImg(pos) {
          pos=pos[0];     //首先获取到图片的原来的url
          this.$http.delete("blogs/admin/img/" + this.img_name).then((res) => {

          });
          delete this.imgs[pos];     //删除之前记录的图像字典中的数据

        },

        tabCheck(release) {
          if (this.blog && this.blog.blog_title && this.blog_type && this.tags && this.blog.blog_description
            && this.blog.blog_content) {
            if (this.$route.query.id) {
              this.putBlog(release);
            } else {
              this.saveBlog(release);
            };
          } else {
            confirm('不能为空！！！')
          }
        },

        newType() {
          this.tags = []

        },

        goToPath() {
          this.$router.replace('/admin')
        },


        reload() {
          this.$router.replace('/admin/blog')
          this.$router.go(0)
        },


      },

      created() {
        if (this.$route.query.id) {
          this.getBlog();
        };

        this.getTypes();

        $( document ).ready(function() {
          $('.ui.dropdown').dropdown()
        });

      }
    }
</script>

<style>

</style>
