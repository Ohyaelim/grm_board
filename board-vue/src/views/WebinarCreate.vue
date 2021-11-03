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
                  v-model="form.roomHost"
                  required
              />
              <v-text-field
                  label="비밀번호"
                  v-model="form.passwd"
                  required
              />
<!--              날짜 입력-->
              <v-row>
                <VueCtkDateTimePicker v-model="form.startDate" label="시작일시" format="YYYY-MM-DD HH:mm"/>
                <VueCtkDateTimePicker v-model="form.endDate" label="종료일시" format="YYYY-MM-DD HH:mm"/>
<!--                <datetime v-model="date" input-id="startDate">-->
<!--                  <label for="startDate" slot="before">Field Label</label>-->
<!--                  <span class="description" slot="after">The field description</span>-->
<!--                  <template slot="button-cancel">-->
<!--                    <fa :icon="['far', 'times']"></fa>-->
<!--                    Cancel-->
<!--                  </template>-->
<!--                  <template slot="button-confirm">-->
<!--                    <fa :icon="['fas', 'check-circle']"></fa>-->
<!--                    Confirm-->
<!--                  </template>-->
<!--                </datetime>-->

<!--                <datetime-->
<!--                    type="datetime" v-model="form.startDate" format="yyyy-MM-dd HH:mm" :minute-step="15">-->
<!--                  시작날짜-->
<!--                </datetime>-->
<!--                <datetime-->
<!--                    type="datetime" v-model="form.endDate" format="yyyy-MM-dd HH:mm" :minute-step="15">-->
<!--                  종료날짜-->
<!--                </datetime>-->

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
        passwd:'',
        roomHost:'',
        startDate:'',
        endDate:'',
      },
    }
  },
  methods:{
    async submitForm() {
      const roomData = {
        roomTitle: this.form.roomTitle,
        roomHost: this.form.roomHost,
        passwd: this.form.passwd,
        startDate: this.form.startDate,
        endDate: this.form.endDate
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