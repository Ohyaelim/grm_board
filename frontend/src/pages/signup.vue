<script src="../api/index.js"></script>
<template>
  <div class="mt-lg-5" style="display: flex; flex-direction: column; align-items: center;">
      <b-img src="../assets/logo.png" style="width: 200px;display:inline-block; margin-bottom: 20px"/>
      <h4 style="display:inline-block; margin-bottom: 20px;">Welcome! Sign Up with Your Account 🙌🏻</h4>
    <b-form @submit.prevent="submitForm" style="display:inline-block;">

      <ValidationProvider
          rules="required"
          v-slot="{ errors} "
      >
          <b-form-group label="Email address:" style="width: 400px;">
            <b-form-input
                v-model="form.email"
                type="email"
                placeholder="abc@gooroomee.com"
                required
            ></b-form-input>
          </b-form-group>
        <span>{{errors[0]}}</span>
      </ValidationProvider>

      <b-form-group label="Password:" style="width: 400px">
        <b-form-input
            type="password"
            v-model="form.password"
            placeholder="Enter password"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Confirm Password:" style="width: 400px">
        <b-form-input
            type="password"
            v-model="form.passwordConfirm"
            placeholder="Password Confirm"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="name:" style="width: 400px">
        <b-form-input
            id="input-1"
            v-model="form.name"
            type="text"
            required
        ></b-form-input>
      </b-form-group>


      <b-button type="submit" variant="primary" style="margin-left: 300px; width:100px">SignUp</b-button>
    </b-form>
  </div>
</template>

<script>
    import {registerUser} from "@/api";
    import {ValidationProvider} from 'vee-validate';
    export default {
      components:{
        ValidationProvider
      },
      name: 'SignupForm',
      data(){
        return{
          form:{
            email: '',
            password: '',
            passwordConfirm: '',
            name:''}
        };
      },
      methods:{
        async submitForm() {
          //validation 생략
          const userData = {
            email: this.form.email,
            password: this.form.password,
            nickname: this.form.name
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
