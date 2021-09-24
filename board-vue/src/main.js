import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import {router} from "@/routes/route";
import axios from 'axios'
import {extend} from 'vee-validate';
import {required, email} from "vee-validate/dist/rules";
import store from "@/store/store";


import './static/MyStyle.css'

Vue.config.productionTip = false

extend('email', email);
extend('required', required);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false

new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch('getMemberInfo')
  },
  vuetify,
  axios,
  render: h => h(App)
}).$mount('#app')
