<template>
  <div class="mt-lg-5"  style="display: flex; flex-direction: column; align-items: center; margin-top: 50px;">
    <b-img src="../assets/logo.png" style="width: 200px;display:inline-block; margin-bottom: 20px"/>
    <h4 style="display:inline-block;margin-bottom: 20px;">Sign In to Your Account </h4>
    <b-form @submit.prevent="signIn" style="display:inline-block;">
      <b-form-group label="Email address:" style="width: 400px;">
        <b-form-input
            v-model="form.email"
            type="email"
            placeholder="abc@gooroomee.com"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Password:" style="width: 400px">
        <b-form-input
            type="password"
            v-model="form.password"
            placeholder="Enter password"
            required
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary" style="margin-left: 300px; width:100px">Login</b-button>
    </b-form>
  </div>
</template>

<script>
import {signInUser} from '@/api';
  export default {
        data(){
          return{
            form: {
              email: '',
              password: ''
            }
          }
        },
    methods:{
         async signIn() {
            const userData = {
              email: this.form.email,
              password: this. form.password,
            };
            const response = await signInUser(userData);
            if(response.status==200){
              localStorage.setItem('X-AUTH-TOKEN', response.data);
              alert('로그인 성공!');
              console.log(response.data);
              await this.$router.push('/');

            }
          }
    }
  }
</script>