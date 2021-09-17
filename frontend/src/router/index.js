import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '@/pages/login'
import Signup from '@/pages/signup'
import PostList from '@/pages/postList'
import PostDetail from '@/pages/postDetail'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/postList',
    name: 'postList',
    component: PostList
  },
  {
    path: "/board/:postId",
    name: "postDetail",
    component: PostDetail
  },
]

const router = new VueRouter({
  mode: 'history',
 base: process.env.BASE_URL,
  routes
})

export default router
