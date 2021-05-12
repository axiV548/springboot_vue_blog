<template>
  <div>
    <admin_navigate></admin_navigate>

    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a href="/admin/albums/input" class="active item">新增</a>
          <a href="/admin/albums" class="item">管理</a>
        </div>
      </div>
    </div>

    <div class="index-footer-big">
      <div class="ui container">
        <div class="ui">
          <div class="six required field">
            <div class="ui left labeled input input_title">
              <input type="text" placeholder="标题" v-model="album.album_name">
              <div class="field">
                <select class="ui dropdown" placeholder="选择所属分类" v-model="album.album_type">
                  <option :value=0></option>
                  <option v-for="aB in albums" :value="aB.album_id" v-if="aB.album_level == 0">{{ aB.album_name }}</option>

                </select>
              </div>
            </div>
          </div>
        </div>

        <div class="required field">
          <textarea class="input_describe" placeholder="相册描述" maxlength="100" v-model="album.album_introduction"></textarea>
        </div>

        <div class="required field ">
          <div class="ui left labeled input home_picture" style="width: 30%">
            <label class="ui teal basic label ">封面</label>
            <input type="file" ref="cover">
            <img :src="service_url+ '/albumImg/' +album.album_cover" style="height: 50px" v-if="album.album_cover" class="ui rounded image">

          </div>
        </div>


        <div class="ui right aligned container">
          <button type="button" @click="tabCheck()" class="ui teal button">创建</button>
          <button type="button" @click="goToPath()" class="ui button">返回</button>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import admin_navigate from "../../../components/admin_navigate";

  export default {
    name: "admin_album_input",
    components:{
      admin_navigate,
    },
    data() {
      return {
        albums: [],
        album: {},
        fAlbum: 0,
        service_url: this.$base_url
      }
    },
    methods: {

      getAlbums() {
        this.$http.get(this.$base_url + "/albums").then((res)=>{
          this.albums = res.data.data;
          for (let i=0; i < this.albums.length; i++) {
            if (this.albums[i].album_id == this.$route.query.id) {
              this.album = this.albums[i]
            }
          }
        });
      },

      saveAlbum() {

        if (this.album.album_type) {
          this.album.album_level = 1
        } else {
          this.album.album_type = 0
          this.album.album_level = 0
        }

        let formData = new FormData;
        for( let key in this.album ){
          //遍历对象属性
          formData.append(key, this.album[key]);
        }
        formData.append("cover", this.$refs.cover.files[0]);
        this.$http({
          method: "post",
          url: this.$base_url + "/albums/admin",
          data: formData,
        }).then((res)=>{
          if (res.data.code == 200) {
            this.goToPath();
          }
        });
      },

      putAlbum() {
        delete this.album.album_create_time

        if (this.album.album_type) {
          this.album.album_level = 1
        } else {
          this.album.album_level = 0
        }
        let formData = new FormData;
        for( let key in this.album ){
          //遍历对象属性
          formData.append(key, this.album[key]);
        }
        formData.append("cover", this.$refs.cover.files[0]);
        this.$http({
          method: "put",
          url: this.$base_url + "/albums/admin/" + this.$route.query.id,
          data: formData,
        }).then((res)=>{
          if (res.data.code == 200) {
            this.goToPath();
          }
        });
      },
      goToPath() {
        this.$router.replace('/admin/albums')
      },

      tabCheck() {
        if (this.album && this.album.album_name && this.album.album_introduction) {
          if (this.$route.query.id) {
            this.putAlbum();
          } else {
            this.saveAlbum();
          };
        } else {
          confirm('不能为空！！！')
        }
      },


    },
    created() {
      this.getAlbums();

    },

  }
</script>

<style scoped>

</style>
