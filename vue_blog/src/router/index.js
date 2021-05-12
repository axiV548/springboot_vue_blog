import Vue from 'vue'
import Router from 'vue-router'
import index from '@/module/index/index'
import blog from '@/module/blog/blog'
import types from '@/module/types/types'
import search from "../module/search/search";

import admin from '@/module/admin/admin'
import login from "../module/admin/login";
import blog_input from "../module/admin/blog_input";
import types_input from "../module/admin/types/type_input";
import admin_types from "../module/admin/types/admin_types";
import about from "../module/about/about";
import album from "../module/photoAlbum/photoAlbum";
import photos from "../module/photoAlbum/photos";
import admin_albums from "../module/admin/albums/admin_albums";
import admin_album_input from "../module/admin/albums/admin_album_input";
import photos_management from "../module/admin/albums/photos_management";
import photo_input from "../module/admin/albums/photo_input";

Vue.use(Router);

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      name: 'index',
      component: index
    },
    {
      path: '/blog',
      name: 'blog',
      component: blog
    },
    {
      path: '/types',
      name: 'types',
      component: types
    },
    {
      path: '/admin',
      name: 'admin',
      component: admin
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/admin/blog',
      name: 'blog_input',
      component: blog_input
    },
    {
      path: '/admin/types',
      name: 'admin_types',
      component: admin_types
    },
    {
      path: '/admin/types/input',
      name: 'types_input',
      component: types_input
    },
    {
      path: '/admin/albums',
      name: 'admin_albums',
      component: admin_albums
    },
    {
      path: '/admin/albums/input',
      name: 'admin_album_input',
      component: admin_album_input
    },
    {
      path: '/admin/albums/photos',
      name: 'photos_management',
      component: photos_management
    },
    {
      path: '/admin/albums/photos/input',
      name: 'photo_input',
      component: photo_input
    },
    {
      path: '/about',
      name: 'about',
      component: about
    },
    {
      path: '/search',
      name: 'search',
      component: search
    },
    {
      path: '/albums',
      name: 'album',
      component: album
    },
    {
      path: '/albums/photos',
      name: 'photos',
      component: photos
    }

  ]
})

