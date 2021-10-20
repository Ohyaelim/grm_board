<template>
  <div>
    <v-btn
        :loading="loading3"
        :disabled="loading3"
        color="#8852a6"
        class="ma-2 white--text"
        @click="loader = 'loading3'"
        to="/webinar/manage"
    >
      ← 목록으로 돌아가기
    </v-btn>
    <h1 align="center">심포지엄 등록</h1>

    <div>
      <v-container>

        <v-card align="center">
          <v-subheader>* 모든 항목을 정확히 입력해주시기 바랍니다.</v-subheader>
          <v-form v-model="valid" @submit.prevent="submitForm">
            <v-container>
              <v-text-field
                  label="심포지엄 제목"
                  required
              />
              <v-text-field
                  label="연자"
                  required
              />
              <v-text-field
                  label="연자정보"
                  required
              />
<!--              날짜 입력-->
              <v-row>
                <DateTimePicker :label="'시작날짜'" required></DateTimePicker>
                <DateTimePicker :label="'종료날짜'" required></DateTimePicker>
              </v-row>
<!--              이미지-->
              <v-file-input
                  show-size
                  label="File input"
              ></v-file-input>

              <v-btn color="#AB47BC" type="submit" :disabled="!valid">확인</v-btn>
            </v-container>
          </v-form>
        </v-card>
      </v-container>
    </div>
  </div>
</template>

<script>
import DateTimePicker from "@/components/DateTimePicker";
import axios from "axios";
import Vue from "vue";

export default {
  name: "WebinarCreate",
  components: {
    DateTimePicker
  },
  data(){
    return{
      valid:false,
      title:'',
      host: '',
      hostInfo:'',
      startDate:'',
      endDate:'',
      file:''
    }
  },
  methods:{
    async submitForm(){
      const BASE_URL = 'https://cors-anywhere.herokuapp.com/https://biz-dev-api.gooroomee.com/api/v1/room'
      const config = {
        headers: {
          "Content-Type" : "application/x-www-form-urlencoded;charset=utf-8",
          "X-GRM-AuthToken" : "1aa271b4af192d114c55199a81cc211093b170481d15119584"
        }
      }
      // axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
      // axios.defaults.headers.post['X-GRM-AuthToken'] = '1aa271b4af192d114c55199a81cc211093b170481d15119584'
      Vue.prototype.$http = axios

      this.$http.post(
          `${BASE_URL}`,
          {roomTitle : '테스트용입니다요'},
          config
      )
      .then((res) => {
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>

</style>