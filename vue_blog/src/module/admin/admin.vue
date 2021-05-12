<template>
  <div>
    <admin_navigate></admin_navigate>
    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a href="/admin/blog" class="item">新增</a>
          <a href="/admin" class="active item">管理</a>
        </div>
      </div>
    </div>


    <!--主页-->
    <div class="index-footer-big">
      <div class="ui container">
<!--        <div class="ui secondary form">-->
<!--          <input type="hidden" name="page">-->
<!--          <div class="inline fields">-->
<!--            <div class="field">-->
<!--              <input type="text" name="title" placeholder="标题">-->
<!--            </div>-->
<!--            <div class="field">-->
<!--              <div class="ui selection dropdown">-->
<!--                <input type="hidden" name="typesId">-->
<!--                <i class="dropdown icon"></i>-->
<!--                <div class="default text">分类</div>-->
<!--                <div class="menu">-->
<!--                  <div th:each="type : ${types}" class="item" data-value="1" th:data-value="${type.id}" th:text="${type.name}"></div>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--            <div class="field">-->
<!--              <div class="ui checkbox">-->
<!--                <input type="checkbox" id="recommendStatus" name="recommendStatus">-->
<!--                <label for="recommendStatus">推荐</label>-->
<!--              </div>-->
<!--            </div>-->
<!--            <div class="field">-->
<!--              <button type="button" id="search-btn" class="ui teal basic button"><i class="search icon"></i>搜索</button>-->

<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
        <div id="table-container">
          <table class="ui table">
            <thead>
            <tr>
              <th>id</th>
              <th>标题</th>
              <th>类型</th>
              <th>推荐</th>
              <th>状态</th>
              <th>更新时间</th>
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="blog in blogs">
              <td>{{ blog.blog_id }}</td>
              <td>{{ blog.blog_title }}</td>
              <td>{{ blog.blog_title }}</td>
              <td>{{ blog.blog_recommend }}</td>
              <td v-if="blog.blog_release">保存</td>
              <td v-if="!blog.blog_release">发布</td>
              <td>{{ blog.blog_update_time }}</td>
              <td>
                <a :href="'/admin/blog?id=' + blog.blog_id" class="ui mini teal basic button">编辑</a>
                <a href="/admin" @click="delBlog(blog.blog_id)" class="ui mini red basic button">删除</a>
              </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
              <th colspan="7">
                <div class="ui mini right floated pagination menu ">
                  <p v-if="page > 1" @click="previousPage()" class="item">上一页</p>
                  <p v-if="page < size/8" @click="nextPage()" class="item">下一页</p>
                </div>
              </th>
            </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import admin_navigate from "../../components/admin_navigate";

  export default {
    name: "admin",
    data () {
      return {
        blogs: [],
        page: 1,
        size: 0
      }
    },
    components:{
      admin_navigate,
    },
    methods: {
      getBlogs(index) {
        this.page = index || this.page
        this.$http.get(this.$base_url + "/blogs/admin/page/" + this.page).then((res)=>{
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
      },
      delBlog(delId) {
        if(confirm('确定要删除吗?')==true) {
            this.$http.delete(this.$base_url + "/blogs/admin/" + delId).then((res)=>{
              if (res.data.code==200) {
                this.$router.go(0)
              }
          });
        };
      },

    },
    created() {
      this.getBlogs();
    }
  }
</script>

<style scoped>

</style>
