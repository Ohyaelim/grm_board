<template>
  <nav>
    <v-app-bar app class="white--text" color="#8852a6" v-if="!drawer">

      <v-app-bar-nav-icon @click.stop="drawer = !drawer" color="white"/>
      <v-btn to="/" icon>
        <v-icon>
          🖊
        </v-icon>
      </v-btn>

      <v-toolbar-title>
        <span v-if="!drawer" t>Get Study With Me 🎠</span>
      </v-toolbar-title>

      <v-spacer/>

      <v-btn v-if="isLogin" to="/myPage" icon>
          👋MY
      </v-btn>
      <v-btn v-else to="/login" icon>
        👋Login
      </v-btn>

      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn icon>
            <v-icon color="white" v-on="on">mdi-apps</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-if="isLogin === true" to="/mypage">
            <v-list-item-title>마이페이지</v-list-item-title>
          </v-list-item>

          <v-list-item
              @click="$store.dispatch('logout')"
              v-if="isLogin === true">
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin===false" to="/login">
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin===false" to="/register">
            <v-list-item-title>회원가입</v-list-item-title>
          </v-list-item>

        </v-list>

      </v-menu>
    </v-app-bar>

    <!-- SECTION: 옆쪽 Drawer
    ====================================================================== -->
    <v-navigation-drawer
        app
        dark
        v-model="drawer"
        absolute
        bottom
        temporary
    >
      <v-list
          nav
          dense
      >
        <v-col class="text-center">
          <h2 class="white--text my-3">
            💕 G.S.W.M 💕
          </h2>
        </v-col>
        <hr class="ma-3 white my-hr"/>
        <v-list-item-group
            active-class="deep-purple--text text--accent-4"
        >
          <v-list-item to="/">
            <v-list-item-title>🧡 Main</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin === false" to="/login">
            <v-list-item-title>💛 로그인</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin === false" to="/register">
            <v-list-item-title>💚 회원가입</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin===true" to="/mypage">
            <v-list-item-title>💛 마이페이지</v-list-item-title>
          </v-list-item>

          <v-list-item @click="$store.dispatch('logout')" v-if="isLogin===true">
            <v-list-item-title>💚 로그아웃</v-list-item-title>
          </v-list-item>

          <v-list-item to="/boards">
            <v-list-item-title>💙 게시판</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin===true" to="/boardWrite">
            <v-list-item-title>💜 글쓰기</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <!-- SECTION: 옆쪽 Drawer -->
  </nav>
</template>

<script>
import {mapState} from "vuex"

export default {
  name: "AppBar",
  data(){
    return{
      drawer: false
    }
  },
  computed: {
    ...mapState(['isLogin'])
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}
</style>