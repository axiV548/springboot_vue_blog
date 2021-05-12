<template>
  <div>
    <admin_navigate></admin_navigate>

    <div class="ui attached pointing menu">
      <div class="ui container">
        <div class="right menu">
          <a href="/admin/albums/input" class="item">新增</a>
          <a href="/admin/albums" class="active item">管理</a>
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

                <li class="albumLi" v-for="albumType in albumTypes">
                  <a :href="'/admin/albums?id=' + albumType.album_id">
                    <div class="box_shadaw">
                      <img :src="service_url+ '/albumImg/' +albumType.album_cover" style="width:100%; height:100%;"/>
                      <div class="imgText">
                        {{ albumType.album_name }}
                      </div>
                    </div>
                  </a>
                  <div style="height: 10%;text-align:center">
                    <a :href="'/admin/albums/input?id=' + albumType.album_id"><div class="ui submit mini green button">编辑</div></a>
                    <div class="ui submit mini red button" @click="delAlbum(albumType.album_id)">删除</div>
                  </div>
                </li>

                <li class="albumLi" v-for="albumTag in albumTags">
                  <a :href="'/admin/albums/photos?id=' + albumTag.album_id">
                    <div class="box_shadaw">
                      <img :src="service_url+ '/albumImg/' +albumTag.album_cover" style="width:100%; height:100%;"/>
                      <div class="imgText">
                        {{ albumTag.album_name }}
                      </div>
                    </div>
                  </a>

                  <div style="height: 10%;text-align:center">
                    <a :href="'/admin/albums/input?id=' + albumTag.album_id"><div class="ui submit mini green button">编辑</div></a>
                    <div class="ui submit mini red button" @click="delAlbum(albumTag.album_id)">删除</div>
                  </div>
                </li>

              </ul>

            </div>
          </div>
        </div>
      </div>
    </div>


  </div>

</template>

<script>

  import admin_navigate from "../../../components/admin_navigate";

  export default {
    name: "admin_albums",
    components:{
      admin_navigate,
    },
    data () {
      return {
        albumTypes: [],
        albumTags: [],
        service_url: this.$base_url


      };
    },
    methods: {
      getAlbums() {
        let albums = []
        let aT = []

        this.$http.get(this.$base_url + "/albums").then((res)=>{
          albums = res.data.data;
          for (let i=0; i < albums.length; i++) {
            if (albums[i].album_type) {
              aT.push(albums[i]);
            } else {
              this.albumTypes.push(albums[i]);
            }
          }

          if (this.$route.query.id) {
            this.getAlbumTags(aT);
          }
        });
      },

      getAlbumTags(aT) {
        this.albumTypes = []
        for (let i=0; i < aT.length; i++) {
          if (this.$route.query.id == aT[i].album_type) {
            this.albumTags.push(aT[i])
          }
        }
        if (!this.albumTags.length) {
          if(confirm('该相册为空，是否创建')==true) {
            this.$router.replace('/admin/albums/input')
          }
        }

      },
      delAlbum(album_id) {
        if(confirm('确定要删除吗?')==true) {
          this.$http.delete(this.$base_url + "/albums/admin/" + album_id).then((res)=>{
            if (res.data.code==200) {
              this.$router.go(0)
            }
          });
        };

      }
    },
    created() {
      this.getAlbums();
    },
  }

</script>

<style scoped>

  .albumLi {
    width: 240px;/*必须，商品项目的宽度*/
    height: auto;/*必须，商品项目的高度度*/
    margin: 7px;/*非必须，为了在中间好看*/

  }
  .imgText {
    position:absolute;
    left:8px;
    bottom:8px;
    color: #f1f1f1;
  }

  .box_shadaw{
    position:relative;
    display:inline-block;
    *display:inline;
    width: 100%;
    height: 90%;
  }
  .box_shadaw:before{
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
    -webkit-box-shadow: 0 -20px 20px black inset;
    -moz-box-shadow: 0 -20px 20px black inset;
    -o-box-shadow: 0 -20px 20px black inset;
  }
</style>
