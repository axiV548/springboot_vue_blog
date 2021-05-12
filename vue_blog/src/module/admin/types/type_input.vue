<template>
  <div>
    <admin_navigate></admin_navigate>
    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a @click="reload" class="item">新增</a>
          <a href="/admin/types" class="active item">管理</a>
        </div>
      </div>
    </div>

    <div class="index-footer-big">
      <div class="ui container">
        <form action="#" method="post" class="ui form">
          <input type="hidden" name="id">
          <div class="two fields">

            <div class="required field">
              <div class="ui left labeled input">
                <label class="ui teal basic label">标签名</label>
                <input type="text" v-model="tag_name">
              </div>
            </div>

            <div class="field">
              <div class="ui left labeled action input">
                <label class="ui compact teal basic label">所属分类</label>
<!--                <div class="ui selection dropdown">-->
<!--                  <i class="dropdown icon"></i>-->
<!--                  <div class="default text">分类</div>-->
<!--                  <div class="menu">-->
<!--                    <div class="item" :value="1">w</div>-->
<!--                    <div class="item" :value="2">w2</div>-->
<!--                    <div class="item" :value="3">w3</div>-->
<!--&lt;!&ndash;                    <div class="item" :t_id="type.type_tag_id" v-for="type in types">{{type.type_tag_name}}</div>&ndash;&gt;-->
<!--                  </div>-->
<!--                </div>-->

                <select class="ui dropdown" v-model="type_id">
                  <option value="0" selected>请选择</option>
                  <option v-for="type in types" :value="type.type_tag_id" v-if="!type.type_tag_level">{{ type.type_tag_name }}</option>
                </select>

              </div>
            </div>
          </div>
          <div class="ui right aligned container">
            <button type="button" @click="tabCheck()" class="ui teal submit button">提交</button>
            <button type="button" @click="goToPath()" class="ui button">返回</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
    import admin_navigate from "../../../components/admin_navigate";
    export default {
      name: "type_input",
      components:{
        admin_navigate,
      },
      data() {
        return {
          tag_name:"",
          type_id:0,
          tag_id:0,
          types:[],
          tag:{}
        }
      },
      methods: {
        getType() {
          this.$http.get(this.$base_url + "/types/admin").then((res)=>{
            this.types = res.data.data;

          });
        },

        postType() {
          if (this.tag_id) {
            if (this.type_id) {
              this.tag.type_tag_id = this.tag_id;
              this.tag.type_tag_name = this.tag_name;
              this.tag.type_parent_id = this.type_id;
              this.tag.type_tag_level = 1;
            } else {
              this.tag.type_tag_tag_id = this.tag_id;
              this.tag.type_tag_name = this.tag_name;
              this.tag.type_parent_id = 0;
              this.tag.type_tag_level = 0;
            };

            this.$http.put(this.$base_url + "/types/admin", this.tag).then((res)=>{
              if (res.data.code==200) {
                this.goToPath();

              }

            });

          } else {
            if (this.type_id) {
              this.tag.type_tag_name = this.tag_name;
              this.tag.type_parent_id = this.type_id;
              this.tag.type_tag_level = 1;
            } else {
              this.tag.type_tag_name = this.tag_name;
              this.tag.type_parent_id = 0;
              this.tag.type_tag_level = 0;
            };

            this.$http.post(this.$base_url + "/types/admin", this.tag).then((res)=>{
              if (res.data.code==200) {
                this.goToPath();

              }
            });
          };

        },

        tabCheck() {
          if (this.tag_name) {
            this.postType();
          } else {
            confirm('标签名不能为空！！！')
          }
        },

        goToPath() {
          this.$router.replace('/admin/types')
        },

        reload() {
          this.$router.go(0)
        },

      },

      created() {
        if (this.$route.query.id) {
          this.tag_id = this.$route.query.id
          this.type_id = this.$route.query.father
          this.tag_name = this.$route.query.name
        };
        this.getType();
        // $( document ).ready(function() {
        //   $('.ui.dropdown').dropdown()
        // });
      }
    };
    // $('.ui.dropdown').dropdown();


</script>

<style scoped>

</style>
