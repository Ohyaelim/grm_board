<template>
  <div>
    <div style="overflow: hidden; width: 100%" >
      <img src="..\assets\yrmyunv.jpg" style="height: 300px; width: 100%">
    </div>
    <v-container>
      <v-card style="width: 30%" height="300">
        <v-card-title align="center">🏳‍🌈 Main WEBINAR :<br>{{room.roomTitle}}</v-card-title>
        <v-card-subtitle><br>🙋‍♀️강연자: {{room.roomHost}}</v-card-subtitle>
        <v-card-text>
          강연시간 : {{DateTime(room.startDate)}}
          <br>~ {{DateTime(room.endDate)}}
        </v-card-text>
        <v-card-text>
          하단의 버튼을 눌러 접속해주세요!
        </v-card-text>
        <v-card-actions>
          <v-btn
              elevation="2" outlined color="purple" width="120" height="30" v-if="true" @click="EntranceGRM(room.roomId)"
          >입장하기</v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {mapState} from "vuex"
import moment from "moment";

export default {
  name: "Home",
  computed: {
    ...mapState(['isLogin'])
  },
  beforeCreate() {
    const token = localStorage.getItem("token");
    if (token) {
      this.$store.state.isLogin = true;
    }
    this.$axios.get(`/webinar`).then((res) => {
      console.log('mainroom'+res.data)
      this.room = res.data
    })
  },
  methods: {
    DateTime(t) {
      return moment(t).format('YYYY-MM-DD hh:mm')
    },
    EntranceGRM(roomId){
      this.$axios.post('/webinar/enter/'+roomId).then((res)=>{
        console.log(res)
        window.open(res.data, "_blank");
      })
    }
  },
  data() {
    return {
      room: [

      ]
    }
  }
}
</script>

<style scoped>

</style>