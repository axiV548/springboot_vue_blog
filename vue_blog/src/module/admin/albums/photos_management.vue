<template>
  <div>
    <admin_navigate></admin_navigate>

    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <input type="file" ref="photoList" multiple="multiple" size="200" class="active item">
          <button @click="uploadPhoto()" class="item">上传</button>
          <div class="active item">管理</div>
        </div>
      </div>
    </div>

    <div class="blog-container index-footer-big">
      <div class="ui container">
        <div class="ui stackable grid">
          <!--左列表-->
          <div class="wide column">
            <div class="ui attached segment">
              <ul class="albumsUl">
                <li class="albumLi" v-for="(photo,index) in photos">
                  <a :href="'/admin/albums/photos/input?id=' + photo.photo_id">
                    <img :src="service_url + '/photoImg/' + photo.photo_save_name" style="width:100%; height:90%;"/>
                  </a>
                  <div class="ui checkbox">
                    <input type="checkbox" v-model="delList[index]" :key="photo.photo_id" name="example">
                    <label>选择</label>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="ui bottom attached segment">
          <div class="ui middle aligned two column grid">
            <div class="column">
              <p v-if="page > 1" @click="previousPage()" class="ui mini blue basic button">上一页</p>
            </div>
            <div class="right aligned column">
              <p v-if="page < size/25" @click="nextPage()" class="ui mini blue basic button">下一页</p>
            </div>
          </div>
        </div>
      </div>
      <div class="ui right aligned container">
        <button type="button" @click="deletePhotos()" class="ui teal button">删除</button>
        <button type="button" @click="goToPath()" class="ui button">返回</button>

      </div>
    </div>
  </div>

</template>

<script>
  import admin_navigate from "../../../components/admin_navigate";

  export default {
    name: "photos_management",
    components: {
      admin_navigate,
      },
      data () {
        return {
          photos: [],
          delList: [],
          page: 1,
          size: 0,
          service_url: this.$base_url

        };
      },
      methods: {
        getPhotos(index) {
          this.page = index || this.page
          this.$http.get(this.$base_url + "/albums/" + this.$route.query.id + "/page/" + this.page).then((res)=>{
            this.photos = res.data.data;
            this.size = res.data.other;

          });

        },

        uploadPhoto() {
          if (this.$refs.photoList.files.length) {
            let formData = new FormData;
            for( let i=0; i<this.$refs.photoList.files.length; i++ ){
              formData.append("photoList", this.$refs.photoList.files[i]);
            }
            this.$http({
              method: "post",
              url: this.$base_url + "/albums/admin/photos/" + this.$route.query.id,
              data: formData,
            }).then((res)=>{
              if (res.data.code==200) {
                this.$router.go(0)
              }
            });

          } else {
            confirm('不能为空！！！')
          }


        },

        goToPath() {
          this.$router.replace('/admin/albums')
        },

        deletePhotos() {

          let delPhotos = [];

          for(let i = 0; i < this.delList.length; i++) {
            if (this.delList[i]) {
              delPhotos.push(this.photos[i].photo_id)
            }
          }
          this.$http({
            method: "delete",
            url: this.$base_url + "/albums/admin/photoIdList",
            data: delPhotos,
          }).then((res)=>{
            if (res.data.code==200) {
              this.$router.go(0)
            }
          });
        },
        nextPage() {
          this.page++;
          this.getPhotos(this.page)
        },

        previousPage() {
          this.page--;
          this.getPhotos(this.page)
        }

      },
      created() {
        this.getPhotos();

      },
  }
</script>

<style scoped>

  .albumLi {
    width: 240px;/*必须，商品项目的宽度*/
    height: 250px;/*必须，商品项目的高度度*/
    margin: 7px;/*非必须，为了在中间好看*/
    background:#ddd;
  }
</style>
