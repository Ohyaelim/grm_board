<template>

  <v-container>
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
    <v-card STYLE="margin-top: 100">
      <v-card-title>
        신청자 리스트^^
        <v-spacer></v-spacer>
      </v-card-title>
      <v-simple-table
          fixed-header
          height="390px"
      >
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              신청자
            </th>
            <th class="text-left">
              이메일
            </th>
            <th class="text-left">
              디바이스종류
            </th>
            <th class="text-left">
              입장시간
            </th>
            <th class="text-left">
              오고나가기
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in pList"
              :key="item.apiUserId"
          >
            <td>{{ item.USERNAME }}</td>
            <td>{{ item.apiUserId }}</td>
            <td>{{ item.osInfo }}</td>
            <td>{{ DateTime(item.regDate) }}</td>
            <td>{{ item.logType }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card>
  </v-container>
</template>

<script>
import moment from "moment";

export default {
  name: "ParticipantsList",
  data() {
    return {
      pList:''
    }
  },
  created () {
    this.participantsList()
  },
  methods: {
    participantsList() {
      let roomId = this.$route.params.roomId;
      this.$axios.get(`/webinar/participants/${roomId}`).then((res) => {
          this.pList = res.data.data.logList[0].logs;
          console.log(res.data.data.logList[0].logs)
        })
      .catch((err) => {
        console.log('너니?'+err)
      })
    },
    DateTime(t) {
      return moment(t).format('📆 YYYY-MM-DD 🧭 hh:mm')
    },
  }
}
</script>

<style scoped>

</style>