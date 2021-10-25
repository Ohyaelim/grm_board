import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import {router} from "@/routes/route";
import axios from 'axios'
import {extend} from 'vee-validate';
import {required, email} from "vee-validate/dist/rules";
import store from "@/store/store";
import VueCtkDateTimePicker from 'vue-ctk-date-time-picker';
import 'vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css';
import moment from "moment"
import VueMomentJS from "vue-momentjs"


import './static/MyStyle.css'

Vue.config.productionTip = false
Vue.component('VueCtkDateTimePicker', VueCtkDateTimePicker);
Vue.use(VueMomentJS,moment)

extend('email', email);
extend('required', required);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false

new Vue({
  router,
  store,
  beforeCreate() {
    const token = localStorage.getItem("token");
    if (token) {
      this.$store.state.isLogin = true;
    }
  },
  vuetify,
  axios,
  render: h => h(App)
}).$mount('#app')
