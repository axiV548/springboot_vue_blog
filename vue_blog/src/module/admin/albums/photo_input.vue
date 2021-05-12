<template>
  <div>
    <admin_navigate></admin_navigate>

    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a href="/admin/albums/photos" class="item">上传</a>
          <a href="/admin/albums" class="active item">管理</a>
        </div>
      </div>
    </div>

    <div class="index-footer-big">
      <div class="ui container">
        <div class="ui segment">
          <img class="ui centered Massive image":src="service_url + '/photoImg/' + photos.photo_save_name">
          <br>
          <div class="required field">
            <textarea class="input_describe" placeholder="照片描述" maxlength="100" v-model="photos.photo_introduction"></textarea>
          </div>

        </div>


        <div class="six required field">
          <div class="ui left labeled input input_title">
            <input type="text" placeholder="输入文件名" v-model="photos.photo_name">
            <div class="field">
              <select class="ui fluid search dropdown" v-model="photos.albums_tag" placeholder="选择所属分类">
                <option value=0></option>
                <option v-for="aB in albums" :value="aB.album_id" v-if="aB.album_level == 1">{{ aB.album_name }}</option>

              </select>
            </div>

          </div>
        </div>

        <div class="ui right aligned container">
          <button type="button" @click="tabCheck()" class="ui teal button">更新</button>
          <button type="button" @click="delPhoto()" class="ui teal button">删除</button>
          <button type="button" @click="goToPath()" class="ui button">返回</button>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import admin_navigate from "../../../components/admin_navigate";

  export default {
    name: "photo_input",
    components:{
      admin_navigate,
    },
    data () {
      return {
        albums: [],
        photos: {},
        service_url: this.$base_url

      };
    },
    methods: {
      getPhotos() {
        // 获取照片信息
        this.$http.get(this.$base_url + "/albums/photos/" + this.$route.query.id).then((res)=>{
          this.photos = res.data.data;


        });

        // 获取分类
        this.$http.get(this.$base_url + "/albums/").then((res)=>{
          this.albums = res.data.data;

        });

      },

      putPhoto() {
        delete this.photos.photo_filming_time
        let formData = new FormData;
        for( let key in this.photos ){
          //遍历对象属性
          formData.append(key, this.photos[key]);
        }

        this.$http({
          method: "put",
          url: this.$base_url + "/albums/admin/photos",
          data: formData,
        }).then((res)=>{
          if (res.data.code == 200) {
            this.goToPath();
          }
        });

      },

      goToPath() {
        this.$router.go(-1);
      },

      tabCheck() {

        if (this.photos && this.photos.photo_id && this.photos.photo_name) {
            this.putPhoto();

        } else {
          confirm('不能为空！！！')
        }
      },
      delPhoto() {
        if(confirm('确定要删除吗?')==true) {

          let delPhotos = [Number(this.$route.query.id)];
          this.$http({
            method: "delete",
            url: this.$base_url + "/albums/admin/photoIdList",
            data: delPhotos,
          }).then((res)=>{
            if (res.data.code==200) {
              this.goToPath();
            }
          });


        };
      },

    },
    created() {
      this.getPhotos();

    },


  }
</script>

<style scoped>

</style>
