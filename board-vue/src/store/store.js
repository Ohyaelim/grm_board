import Vue from "vue"
import Vuex from "vuex"
import {router} from "@/routes/route";
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: null,
        isLogin: false,
        isLoginError: false
    },
    mutations: {
        // islogin을 true로 만들던가 isloginerror를 true로 만들던가

        //로그인이 성공했을 때
        loginSuccess(state, payload){
            state.isLogin = true
            state.isLoginError = false
            state.userInfo = payload
        },
        //로그인이 실패했을 때
        loginError(state) {
            state.isLogin = false
            state.isLoginError = true
        },
        logout(state){
            state.isLogin = false
            state.isLoginError = false
            state.userInfo = null
        }
    },
    actions: {
        // 로그인 시도
        // commit 사용 그리고 payload 인자값이 들어온다
        login({dispatch}, loginObj){ // eslint-disable-line no-unused-vars
            // 로그인 -> 토큰 반환
            axios
                .post('/auth/signin', loginObj) // post방식은 request바디에 파라미터를 넣어줄 수 있다
                .then(res => {
                    console.log('@@ res', res)
                    // 성공시 token 어쩌구가 넘어와
                    // 토큰을 헤더에 포함시켜서 유저 정보 불러와
                    let token = res.data
                    // 토큰을 로컬 스토리지에 저장
                    localStorage.setItem("token", token)
                    dispatch("getMemberInfo")
                    router.push('/')
                })
                .catch(() => {
                    alert("이메일과 비밀번호를 확인하세요~!")
                });
        },
        logout({commit}){
            localStorage.removeItem("token")
            commit('logout')
            router.push('/')
        },
        getMemberInfo({commit}, token){
            // 로컬 스토리지에 저장되어있는 토큰을 불러온다.
            localStorage.getItem("token", token)
            commit("loginSuccess", token)
        }
    }
})