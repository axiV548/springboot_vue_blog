<template>

  <div>
    <admin_navigate></admin_navigate>
    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a href="/admin/types/input" class="item">新增</a>
          <a href="/admin/types" class="active item">管理</a>
        </div>
      </div>
    </div>

    <!--主页-->
    <div class="index-footer-big">
      <div class="ui container">

        <table class="ui table">
          <thead>
          <tr>
            <th>ID</th>
            <th>名称</th>
            <th>属性</th>
            <th>所属分类</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="type in types">
            <td>{{ type.type_tag_id }}</td>
            <td>{{ type.type_tag_name }}</td>
            <td v-if="type.type_tag_level">标签</td>
            <td v-if="!type.type_tag_level">分类</td>
            <td>{{ type.type_parent_id }}</td>

            <td>
              <a :href="'/admin/types/input?id=' + type.type_tag_id + '&father=' + type.type_parent_id + '&name=' + type.type_tag_name" class="ui mini teal button">编辑</a>
              <a href="/admin/types" @click="delType(type.type_tag_id)" class="ui mini read button">删除</a>
            </td>
          </tr>
          </tbody>
          <tfoot>
          <tr>
            <th colspan="6">
              <div class="ui mini right floated pagination menu ">
                <p v-if="page > 1" @click="previousPage()" class="item">上一页</p>
                <p v-if="page < size/10" @click="nextPage()" class="item">下一页</p>
              </div>
            </th>
          </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>

</template>

<script>
    import admin_navigate from "../../../components/admin_navigate";

    export default {
      name: "admin_types",
      data () {
        return {
          types:[],
          page: 1,
          size: 0
        }
      },
      components:{
        admin_navigate,
      },
      methods:{

        getTypes(index) {
          this.page = index || this.page

          this.$http.get(this.$base_url + "/types/admin/page/" + this.page).then((res)=>{
            this.types = res.data.data;
            this.size = res.data.other;

          });
        },
        nextPage() {
          this.page++;
          this.getTypes(this.page)

        },

        previousPage() {
          this.page--;
          this.getTypes(this.page)
        },

        delType(delId) {
          if (confirm('确定要删除吗?') == true) {
            this.$http.delete(this.$base_url + "/types/admin/" + delId).then((res) => {
              if (res.data.code==200) {
                this.$router.go(0)
              }
            });
          }
        },
      },
      created() {
        this.getTypes();

      }
    }
</script>

<style scoped>

</style>
