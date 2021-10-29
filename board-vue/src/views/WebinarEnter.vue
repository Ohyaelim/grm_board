<template>
  <v-container>
    <v-card >
      <v-card-title>
        📍User용, 심포지엄 둘러보기
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
              호스트
            </th>
            <th class="text-left">
              웨비나기간
            </th>
            <th class="text-left">
              입장
            </th>
          </tr>
          </thead>
          <tbody
              v-for="item in webinarList"
              :key="item.roomTitle">
          <tr
              v-if="item.isDeleted == false"
          >
            <!--            <td>{{ item.postId }}</td>-->
            <td>{{ item.roomTitle }}</td>
            <td>{{ item.roomHost }}</td>
            <td>{{ DateTime(item.startDate) }}<br>~ {{ DateTime(item.endDate) }}</td>
            <td>
              <v-btn
              elevation="2" outlined color="purple" width="120" height="40" v-if="true" @click="EntranceGRM(item.roomId)"
              >입장하기</v-btn>
              <v-btn
                  elevation="2" outlined color="purple" width="120" height="40" v-else disabled @click="EntranceGRM(item.roomId)"
              >종료</v-btn>
            </td>
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

export default {
  name: "Boards",
  computed: {
    ...mapState(['isLogin']),
  },
  data() {
    return {
      page: 1,
      totalpages:'',
      webinarList:''
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
            console.log('길이'+res.data.content.length)
            console.log('날짜' + moment(res.data.content.endDate).isAfter(moment()))
            console.log('totalpage '+this.datas.totalPages)
          })
          .catch((err) => {
            console.log('에러'+err);
          })
    },
    EntranceGRM(roomId){
      this.$axios.post('/webinar/enter/'+roomId).then((res)=>{
        console.log(res)
        window.open(res.data, "_blank");
      })
    },
    DateTime(t) {
      return moment(t).format('📆 YYYY-MM-DD 🧭 hh:mm')
    },
    computed: {
      rows() {
        return this.datas.content.length
      }
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
</style>