<template>
  <div>
    <navigate></navigate>
    <div class="blog-container index-footer-big">
      <div class="ui container">
        <div class="ui stackable grid">
          <!--左列表-->
          <div class="wide column">
            <div class="ui attached segment">
              <ul class="albumsUl">

                <li class="albumLi" v-for="albumType in albumTypes">
                  <a :href="'/albums?id=' + albumType.album_id">
                    <div class="box_shadaw">
                      <img :src="service_url+ '/albumImg/' +albumType.album_cover" style="width:100%; height:100%;"/>
                      <div class="imgText">
                        {{ albumType.album_name }}
                      </div>
                    </div>
                  </a>
                </li>

                <li class="albumLi" v-for="albumTag in albumTags">
                  <a :href="'/albums/photos?id=' + albumTag.album_id">
                    <div class="box_shadaw">
                      <img :src="service_url+ '/albumImg/' +albumTag.album_cover" style="width:100%; height:100%;"/>
                      <div class="imgText">
                        {{ albumTag.album_name }}
                      </div>
                    </div>
                  </a>
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
  import navigate from "../../components/navigate";

  export default {
    name: "album",
    components:{
      navigate
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
          if(confirm('该相册为空，是否返回')==true) {
            this.$router.replace('/admin/albums/input')
          }
        }

      },

    },
    created() {
      this.getAlbums();
    },
  }
</script>

<style scoped>

  /*2. 项目的声明*/
  .albumLi {
    width: 444px;/*必须，商品项目的宽度*/
    height: 250px;/*必须，商品项目的高度度*/
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
    height: 100%;
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
