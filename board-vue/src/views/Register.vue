<template>
  <div>
    <v-container>
      <v-card align="center">
        <v-subheader>회원가입</v-subheader>
        <v-form ref="form" v-model="valid" @submit.prevent="submitForm">
          <v-container>
            <ValidationProvider
                rules="required"
                v-slot="{ errors} "
            >
              <v-text-field
                  v-model="email"
                  label="이메일"
                  type="email"
                  :rules="emailRules"
                  required
              />
              <span>{{errors[0]}}</span>
            </ValidationProvider>

            <v-text-field
                v-model="nickname"
                label="이름"
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
import {ValidationProvider} from 'vee-validate';

export default {
  components:{
    ValidationProvider
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
        v=> /.+@.+/.test(v) || '이메일이 유효하지 않습니다.',
      ],
      nameRules: [
        v => !!v || '이름은 필수입니다.',
      ],
      passwordRules: [
        v => !!v || '패스워드는 필수입니다.',
      ],
      passwordCheckRules: [
        v => !!v || '패스워드 확인은 필수입니다.',
        v =>  v === this.password || '비밀번호가 일치하지 않습니다.',
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