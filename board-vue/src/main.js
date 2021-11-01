import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import {router} from "@/routes/route";
import axios from 'axios'
import {extend} from 'vee-validate';
import {required, email} from "vee-validate/dist/rules";
import store from "@/store/store";
import {Datetime} from "vue-datetime";
import moment from "moment"
import 'vue-datetime/dist/vue-datetime.css'
import VueMomentJS from "vue-momentjs"
import {Settings} from "luxon";


import './static/MyStyle.css'


Vue.component('datetime', Datetime);
Vue.config.productionTip = false
Vue.use(VueMomentJS,moment)

extend('email', email);
extend('required', required);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
Settings.defaultLocale = 'es'

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
