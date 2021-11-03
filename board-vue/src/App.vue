<template>
  <div>
    <v-app>
<!--      <AppBar/>-->
<!--      <div id="top">-->
<!--        <span class="header_user">-->
<!--          <a href="" v-if="isLogin === true"><p style="font-weight: bold; letter-spacing: 1px;">님</p></a>-->
<!--          <a href="" v-if="isLogin === true">로그아웃</a>-->
<!--          <a href="" v-if="isLogin === false">로그인</a>-->
<!--          <a href="" v-if="isLogin === false">회원가입</a>-->
<!--        </span>-->
<!--        <hr />-->
<!--      </div>-->
      <div class="base">
        <div class="header">
          <router-link to="/" id=name><h1>👩‍🚀Webinar</h1></router-link>
          <span class="header_menu">
            <router-link to="/boards">공부게시판</router-link>
            <router-link to="/webinar/enter">웨비나목록</router-link>
            <router-link to="/webinar/manage">웨비나관리</router-link>
<!--          <a href="">일정관리</a>-->
<!--          <a href="">Q&A</a>-->
        </span>
          <span class="header_user">
            <router-link to="/mypage" v-if="isLogin === true"><p style="font-weight: bold; letter-spacing: 1px;">🙆‍♀️오예림님</p></router-link>
<!--            <router-link to="to" @click="$store.dispatch('logout')" v-if="isLogin === true">로그아웃</router-link>-->
<!--            <router-link to="to" @click="logout" v-if="isLogin === true">로그아웃</router-link>-->
            <a href="" v-if="isLogin === true"><span @click="$store.dispatch('logout')">로그아웃</span></a>
            <router-link to="/login" v-if="isLogin === false">로그인</router-link>
            <router-link to="/register" v-if="isLogin === false">회원가입</router-link>
        </span>
        </div>
        <hr />
      </div>
      <v-main style="height: 1000px">
        <router-view/>
      </v-main>
<!--      <Footer/>-->
      <div class="footer">
        <span>&copy;2021 Gooroomee 오예림</span>
        <span>contact: yaelim1122@gooroomee.com</span>
      </div>
    </v-app>
  </div>
</template>

<script>
// import AppBar from "@/components/AppBar";
// import Footer from "@/components/Footer";
import {mapState} from "vuex"

export default {
  name: 'App',

  components: {
    // Footer,
    // AppBar
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    logout() {
      console.log('@@@@@@@@@@@@@')
      console.log('@@@@@@@@@@@@@')
    },
  }
};
</script>
<style>
.top {
  border-bottom: 1px
  solid #d9d9d9;
  height: 40px;
}
* {
  font-family: 'SBAggroM';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroM.woff') format('woff');
  /*font-family: "SB", serif;*/
}
.header {
  display: flex;
  background-color: #efd8ec;
  justify-content: space-between;
  align-items: center;
  height: 80px;
  padding-left: 8vw;
  padding-right: 8vw;
}
.header h1 {
  font-family: 'SF_IceLemon';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/SF_IceLemon.woff') format('woff');
  text-align: center;
  display: inline-block;
  font-size: 2.5rem;
  color: #512da8;
  letter-spacing: 0.1rem;
}
.base hr {
  height: 12px;
  border: 0;
  box-shadow: inset 0 12px 12px -12px rgba(61, 61, 61, 0.5);
}
.top hr {
  color: gray;
  height: 10px;
  border: 0;
  box-shadow: inset 0 12px 12px -12px rgba(61, 61, 61, 0.5);
}
.header_menu {
  flex-shrink: 0;
}
.header_menu a {
  font-size: 1.2rem;
  letter-spacing: 0.1rem;
  margin-left: 3vw;
  margin-right: 3vw;
}
.header_menu a:hover {
  color: #512da8;
  font-weight: bold;
}
.header_user {
  text-align: right;
  flex-shrink: 0;
}
.header_user a {
  color: rgb(100, 100, 100);
  font-size: 1.1rem;
  margin-left: 1vw;
}
.header_user p {
  font-size: 1.1rem;
  color: #512da8;
  display: inline-block;
}
a {
  text-decoration: none;
  color: black;
}
.copyright {
  background-color: silver;
}
.footer {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  background-color: rgb(230, 230, 230);
}
.footer span {
  color: rgb(95, 95, 95);
  font-size: 0.9rem;
  margin: 0.5rem;
  background-color: rgb(230, 230, 230);
}
</style>
