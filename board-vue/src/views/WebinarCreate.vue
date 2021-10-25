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
    <h1 align="center">WEBINAR REGISTER</h1>

    <div>
      <v-container>

        <v-card align="center">
          <v-subheader>* 모든 항목을 정확히 입력해주시기 바랍니다.</v-subheader>
          <v-form v-model="valid" @submit.prevent="submitForm">
            <v-container>
              <v-text-field
                  label="웨비나 제목"
                  v-model="form.roomTitle"
                  required
              />
              <v-text-field
                  label="연자"
                  required
              />
              <v-text-field
                  label="비밀번호"
                  v-model="form.passwd"
                  required
              />
<!--              날짜 입력-->
              <v-row>
<!--                <DateTimePicker :label="'시작날짜'" required></DateTimePicker>-->
<!--                <DateTimePicker :label="'종료날짜'" required></DateTimePicker>-->
                <VueCtkDateTimePicker v-model="startDate" label="시작일시" format="YYYY-MM-DD HH:mm"/>
                <VueCtkDateTimePicker v-model="endDate" label="종료일시" format="YYYY-MM-DD HH:mm"/>

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
import {createRoom} from "@/apis";

export default {
  name: "WebinarCreate",
  components: {
  },
  data(){
    return{
      valid:false,
      form: {
        roomTitle:'',
        passwd:''
      },
      startDate:'',
      endDate:'',
    }
  },
  methods:{
    async submitForm(){

      const roomData = {
        roomTitle: this.form.roomTitle,
        passwd: this.form.passwd,
        startDate: this.startDate,
        endDate: this.endDate
      }

      console.log(roomData)

      const response = await createRoom(roomData);
      if (response.status == 200) {
        alert('웨비나 등록 완료^^;');
        await this.$router.push('/webinar/manage');
      } else {
        alert(response.data);
      }

    }
  }
}
</script>

<style scoped>

</style>