import Vue from 'vue'
import Router from 'vue-router'
import index from '@/module/index/index'
import blog from '@/module/blog/blog'
import types from '@/module/types/types'
import search from "../module/search/search";

import admin from '@/module/admin/admin'
import login from "../module/admin/login";
import blog_input from "../module/admin/blog_input";
import types_input from "../module/admin/type_input";
import admin_types from "../module/admin/admin_types";
import about from "../module/about/about";


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
      path: '/about',
      name: 'about',
      component: about
    },
    {
      path: '/search',
      name: 'search',
      component: search
    }

  ]
})

