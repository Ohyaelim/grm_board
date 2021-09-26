<template>
  <v-container fill-height style="width: 450px">
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert
            class ="mb-3"
            :value="isLoginError"
            dense
            outlined
            type="error"
        >
          <strong>아이디</strong>와 <strong>비밀번호</strong>를 확인해주세요!
        </v-alert>
        <v-alert
            class ="mb-3"
            :value="isLogin"
            dense
            outlined
            type="success"
        >
          로그인이 완료되었습니다~!!
        </v-alert>
        <v-card v-if="isLogin===false">
          <v-toolbar flat>
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>

          <div class="pa-3">
            <v-text-field
                v-model="email"
                label="이메일을 입력하세요."
            ></v-text-field>
            <v-text-field
                v-model="password"
                type="password"
                label="패스워드를 입력하세요."
            ></v-text-field>

            <v-btn
                color="#AB47BC"
                large
                block
                depressed
                @click="login({
                  // email: email,
                  email,
                  // password: password 이멜과 패스워드를 store.js의 signinObj로 넘기겠다
                  password,
                })"
                @keyup.enter="
                login({
                  // email: email,
                  email,
                  // password: password 이멜과 패스워드를 store.js의 signinObj로 넘기겠다
                  password,
                })
              "
            >
              로그인</v-btn>
          </div>

        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapState, mapActions} from "vuex" // eslint-disable-line no-unused-vars

export default {
  data(){
    return{
      email: null,
      password: null,

    }
  },
  computed: {
    ...mapState(["isLogin", "isLoginError"])
  },
  methods: {
    ...mapActions(["login"]),
  }
};
</script>

<style scoped>

</style>