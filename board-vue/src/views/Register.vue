<template>
  <div>
    <v-container>
      <v-card align="center">
        <v-subheader>회원가입</v-subheader>
        <v-form ref="form" v-model="valid" @submit.prevent="submitForm">
          <v-container>
            <v-text-field
                v-model="email"
                label="이메일"
                type="email"
                :rules="emailRules"
                required
            />
<!--            <ValidationProvider-->
<!--                rules="required"-->
<!--                v-slot="{ errors} "-->
<!--            >-->
<!--              <v-text-field-->
<!--                  v-model="email"-->
<!--                  label="이메일"-->
<!--                  type="email"-->
<!--                  :rules="emailRules"-->
<!--                  required-->
<!--              />-->
<!--              <span>{{errors[0]}}</span>-->
<!--            </ValidationProvider>-->

            <v-text-field
                v-model="nickname"
                label="닉네임"
                type="text"
                :rules="nameRules"
                required
            />
            <v-text-field
                v-model="password"
                label="비밀번호"
                type="password"
                :rules="passwordRules"
                required
            />
            <v-text-field
                v-model="passwordCheck"
                label="비밀번호 확인"
                type="password"
                :rules="passwordCheckRules"
                required
            />
            <v-checkbox
                v-model="terms"
                :rules="[v=>!!v || '약관에 동의해야 합니다.']"
                required
                label="정보 수집에 동의합니다."
            />
            <v-btn color="#AB47BC" type="submit" :disabled="!valid">가입하기</v-btn>
          </v-container>
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {registerUser} from "@/apis";
// import {ValidationProvider} from 'vee-validate';

export default {
  components:{
    // ValidationProvider
  },
  name: "Register",
  data(){
    return{
      valid:false,
      email:'',
      password:'',
      passwordCheck:'',
      nickname:'',
      terms:false,
      emailRules: [
        v=> !!v || '이메일은 필수입니다.',
        v=> /.+@.+.com+/.test(v) || '이메일이 유효하지 않습니다.',
        v=> /[a-zA-Z]/.test(v) || '한글은 입력을 허용하지 않습니다.',
        v=>(v.length<254) || '이메일은 254자 이상 입력할 수 없습니다.'
      ],
      nameRules: [
        v => !!v || '닉네임은 필수입니다.',
        v => /[ㄱ-ㅎㅏ-ㅣ가-힣]/.test(v) || '닉네임은 한글만 입력하세요',
        v => (v.length<101) || '닉네임은 100자 이하만 입력하세요.',
        v => (v.length>1) || '닉네임은 한글자 이상 입력하세요.',
      ],
      passwordRules: [
        v => !!v || '패스워드는 필수입니다.',
        v=> /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(v) || '패스워드는 영대소문자, 숫자, 특수기호가 포함된 8자 이상만 가능합니다.',
        v => (v.length>7) || '패스워드는 8자보다 길게 써주세요.',
        v => (v.length<21) || '패스워드는 20자를 넘기지마세요.',
      ],
      passwordCheckRules: [
        v => !!v || '패스워드 확인은 필수입니다.',
        v =>  v === this.password || '비밀번호가 일치하지 않습니다.'
      ]
    }
  },
  methods:{
    async submitForm() {
      //validation 생략
      const userData = {
        email: this.email,
        password: this.password,
        nickname: this.nickname
      };

      const response = await registerUser(userData);
      console.log(response)
      if (response.status == 200) {
        alert('환영합니다.');
        await this.$router.push('/login');
      } else {
        alert(response.data);
      }
    },

  }
}
</script>

<style scoped>

</style>