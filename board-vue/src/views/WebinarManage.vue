<template>
  <v-container>
    <v-btn
        :loading="loading3"
        :disabled="loading3"
        color="#8852a6"
        class="white--text"
        @click="loader = 'loading3'"
        style="margin-left: 860px; margin-bottom: 15px"
        to="/webinar/create"
    >
      웨비나생성
    </v-btn>
    <v-card>
      <v-card-title>
        📍Admin용, 심포지엄 목록 관리
        <v-spacer></v-spacer>
      </v-card-title>

      <v-simple-table
          fixed-header
          height="800px"
          width="100%"
      >
        <template v-slot:default>
          <thead>
          <tr>
<!--            <th class="text-left">-->
<!--              No-->
<!--            </th>-->
            <th class="text-left">
              호스트
            </th>
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
            <td>{{ item.roomHost }}</td>
            <td>{{ item.roomTitle }}</td>
            <td>{{ item.passwd }}</td>
<!--            <td>{{ item.startDate }}</td>-->
            <td>{{ DateTime(item.startDate) }}</td>
<!--            <td>{{ item.endDate }}</td>-->
            <td>{{ DateTime(item.endDate) }}</td>
            <td>
              <div class="btn">
                <v-btn v-model="data.pinRoom" v-if="item.isPinned == true " outlined color="purple" @click="RegisterMain(item.id)">
                  이미메인;</v-btn>
                <v-btn v-model="data.pinRoom" v-else-if="item.isPinned == false" @click="RegisterMain(item.id)">
                  메인등록</v-btn>
                <v-btn v-model="data.pinRoom" v-if="item.isDeleted == false" @click="update = true">
                  수정</v-btn>
                <ddd v-if="update" @close="update=false">
                  <h1>hi</h1>
                </ddd>
                <v-btn v-model="data.pinRoom" v-if="item.isDeleted == false" @click="DeleteWebinar(item.id)">
                  삭제</v-btn>
              </div>
            </td>
            <td><v-btn
                elevation="2" outlined color="purple" width="120" height="40"  @click="$router.push({name: 'ParticipantsList', params: { roomId: item.roomId },})"
            >보기</v-btn></td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
      <div class="text-center">
        <v-pagination
            v-model="page"
            :length="totalpages"
            @input="handlePageChange"
            circle
        ></v-pagination>
      </div>
    </v-card>

  </v-container>
</template>

<script>
import {mapState} from "vuex"
import moment from 'moment'
import {mainPinned} from "@/apis";
import {deleteRoom} from "@/apis";

export default {
  name: "Boards",
  computed: {
    ...mapState(['isLogin'])
  },
  data() {
    return {
      webinarList:'',
      page: 1,
      totalpages:'',
      update: false,
      data: {
        pinRoom: ''
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
            this.totalpages = res.data.totalPages;
            console.log(res.data.content)
          })
          .catch((err) => {
            console.log(err);
          })
    },
    DateTime(t) {
      return moment(t).format('📆 YYYY-MM-DD 🧭 hh:mm')
    },
    today() {
      const today = moment()
      console.log(today)
      return today
    },
    RegisterMain(id) {
      mainPinned(id)
      window.location.reload()
    },
    DeleteWebinar(id) {
      deleteRoom(id)
      window.location.reload()
    },
    handlePageChange(value) {
      this.page = value

      this.$axios.get(`/webinar/list`+'?page='+(value-1))
          .then((res) => {
            this.webinarList = res.data.content;
            console.log(res.data.content)
          }).catch((err) => {
        console.log(err);
      })
    }
  }
}
</script>

<style scoped>
.btn {
  align-items: center;
}
</style>