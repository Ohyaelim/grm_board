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
import BoardModify from "@/views/BoardModify";
import MyBoards from "@/views/MyBoards";
import WebinarEnter from "@/views/WebinarEnter";
import WebinarManage from "@/views/WebinarManage";
import WebinarCreate from "@/views/WebinarCreate";
import ParticipantsList from "@/views/ParticipantsList";

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
    if (store.state.userInfo === null){
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
        beforeEnter: rejectAuthUser,
        component: Login
    },
    {
        path: '/mypage',
        name: 'MyPage',
        beforeEnter: onlyAuthUser,
        component: MyPage
    },
    {
        path: '/boards',
        name: 'Boards',
        component: Boards
    },
    {
        path: '/boardWrite',
        name: 'BoardWrite',
        beforeEnter: onlyAuthUser,
        component: BoardWrite
    },
    {
        path: '/boardDetail/:postId',
        name: 'BoardDetail',
        component: BoardDetail
    },
    {
        path: '/boardModify/:postId',
        name: 'BoardModify',
        beforeEnter: onlyAuthUser,
        component: BoardModify
    },
    {
        path: '/mypage/myBoards/:memberId',
        name: 'MyBoards',
        beforeEnter: onlyAuthUser,
        component: MyBoards
    },
    {
        path: '/webinar/enter',
        name: 'WebinarEnter',
        beforeEnter: onlyAuthUser,
        component: WebinarEnter
    },
    {
        path: '/webinar/manage',
        name: 'WebinarManage',
        beforeEnter: onlyAuthUser,
        component: WebinarManage
    },
    {
        path: '/webinar/create',
        name: 'WebinarCreate',
        beforeEnter: onlyAuthUser,
        component: WebinarCreate
    },
    {
        path: '/webinar/Participants',
        name: 'ParticipantsList',
        beforeEnter: onlyAuthUser,
        component: ParticipantsList
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export {router}
