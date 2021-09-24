import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/Home";
import Login from "@/views/Login";
import Boards from "@/views/Boards";
import Register from "@/views/Register";
import MyPage from "@/views/MyPage";
import store from "@/store/store";
import BoardWrite from "@/views/BoardWrite";
import BoardDetail from "@/views/BoardDetail";

Vue.use(VueRouter)

const rejectAuthUser = (to,from, next) => {
    if (store.state.isLogin === true){
        //이미 로그인 된 유저니까 막자
        alert('이미 로그인을 하였습니다.')
        next('/')
    }else {
        next()
    }
}

const onlyAuthUser = (to,from, next) => {
    if (store.state.isLogin === false){
        //아직 로그인 안 된 유저니까 막자
        alert('로그인이 필요합니다.')
        next('/')
    }else {
        next()
    }
}

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/login',
        name: 'Login',
        beforeEnter: rejectAuthUser, //라우터로 들어오기 전에 이 함수를 실행시킨다.
        component: Login
    },
    {
        path: '/mypage',
        name: 'MyPage',
        beforeEnter: onlyAuthUser, // 아직 구현 안함
        component: MyPage
    },
    {
        path: '/boards',
        name: 'Boards',
        component: Boards // 구현중
    },
    {
        path: '/boardWrite',
        name: 'BoardWrite', // 구현중
        beforeEnter: onlyAuthUser,
        component: BoardWrite
    },
    {
        path: '/boardDetail/:postId', // 아직 구현안함함
       name: 'BoardDetail',
        component: BoardDetail
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export {router}
