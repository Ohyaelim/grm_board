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
                :rules="emailRules"
                label="이메일을 입력하세요."
            ></v-text-field>
            <v-text-field
                v-model="password"
                type="password"
                :rules="pwdRules"
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
                @keyup.enter="login({ email,password})"
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
      emailRules: [
        v=> !!v || '이메일을 입력해주세요.',
        v=> /.+@.+.com+/.test(v) || '이메일 형식을 지켜주세요.',
        v=> /[a-zA-Z]/.test(v) || '한글은 입력을 허용하지 않습니다.',
        v=>(v.length<254) || '이메일은 254자 이상 입력할 수 없습니다.'
      ],
      pwdRules: [
        v => !!v || '패스워드는 필수입니다.',
        v=> /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(v) || '패스워드는 영대소문자, 숫자, 특수기호가 포함된 8자 이상만 가능합니다.',
        v => (v.length>7) || '패스워드는 8자보다 길게 써주세요.',
        v => (v.length<21) || '패스워드는 20자를 넘기지마세요.',
      ]
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