import Vue from "vue"
import Vuex from "vuex"
import {router} from "@/routes/route";
import axios from "axios";
import { getMyInfoApi } from "../apis/index";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: localStorage.token === null ? null : localStorage.token,
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
        login({ commit }, loginObj) {
            // eslint-disable-line no-unused-vars
            // 로그인 -> 토큰 반환
            axios
                .post("/auth/signin", loginObj) // post방식은 request바디에 파라미터를 넣어줄 수 있다
                .then((res) => {
                    console.log("@@ res", res);
                    // 성공시 token 어쩌구가 넘어와
                    // 토큰을 헤더에 포함시켜서 유저 정보 불러와
                    let token = res.data;
                    // 토큰을 로컬 스토리지에 저장
                    localStorage.setItem("token", token);
                    commit("loginSuccess", token);
                    //   dispatch("getMemberInfo");
                    router.push("/");
                })
                .catch(() => {
                    alert("이메일과 비밀번호를 확인하세요~!");
                });
        },
        logout({commit}){
            localStorage.removeItem("token")
            commit('logout')
            router.push('/')
        },
        async getMemberInfo({ commit }) {
            const myInfo = await getMyInfoApi()
                .then((res) => {
                    console.log("getMemberInfo", res.data);
                    return res.data;
                })
                .catch((err) => {
                    console.error(err);
                    return {};
                });
            commit("setUserInfo"); // 전역 상태로 지정하고 싶다면..
            return myInfo;
        },
        async putMemberInfo() {
            // 수정 요청 넣고
            // 전역 상태 변경
        },
    }
})