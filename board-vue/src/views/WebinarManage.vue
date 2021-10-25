<template>
  <v-container>
    <v-btn
        :loading="loading3"
        :disabled="loading3"
        color="#8852a6"
        class="ma-2 white--text"
        @click="loader = 'loading3'"
        to="/webinar/create"
    >
      웨비나생성
    </v-btn>
    <v-card >
      <v-card-title>
        📍Admin용, 심포지엄 목록 관리
        <v-spacer></v-spacer>
      </v-card-title>

      <v-simple-table
          fixed-header
          height="300px"
      >
        <template v-slot:default>
          <thead>
          <tr>
<!--            <th class="text-left">-->
<!--              No-->
<!--            </th>-->
            <th class="text-left">
              제목
            </th>
            <th class="text-left">
              비번
            </th>
            <th class="text-left">
              시작일자
            </th>
            <th class="text-left">
              마감일자
            </th>
            <th class="text-left">
              📍
            </th>
            <th class="text-left">
              신청자 목록
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in webinarList"
              :key="item.roomTitle"
          >
<!--            <td>{{ item.postId }}</td>-->
            <td>{{ item.roomTitle }}</td>
            <td>{{ item.passwd }}</td>
<!--            <td>{{ item.startDate }}</td>-->
            <td>{{ DateTime(item.startDate) }}</td>
<!--            <td>{{ item.endDate }}</td>-->
            <td>{{ DateTime(item.endDate) }}</td>
            <td><v-col><v-checkbox v-model="pinRoom"/></v-col></td>
            <td><v-btn
                elevation="2" outlined color="purple" width="120" height="40" to="/webinar/Participants"
            >보기</v-btn></td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
      <div class="text-center">
        <v-pagination
            v-model="page"
            :length="4"
            circle
        ></v-pagination>
      </div>
    </v-card>

  </v-container>
</template>

<script>
import {mapState} from "vuex"
import moment from 'moment'

export default {
  name: "Boards",
  computed: {
    ...mapState(['isLogin'])
  },
  data() {
    return {
      webinarList:'',
      data: {
        pinRoom: []
      }
    };
  },
  created () {
    this.roomList()
  },
  methods: {
    roomList() {
      this.$axios.get(`/webinar/list`)
          .then((res) => {
            this.webinarList = res.data.content;
            console.log(res.data.content)
          })
          .then((err) => {
            console.log(err);
          })
    },
    DateTime(t) {
      return moment(t).format('📆 YYYY-MM-DD 🧭 hh:mm')
    }
  }
}
</script>

<style scoped>
</style>