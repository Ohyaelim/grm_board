<template>
  <div>
    <div class="image" style="height: 380px" >
      <img src="..\assets\yrmyunv.jpg" style="margin-top:20px; height: 100%; width: 100%; object-fit: cover; overflow: hidden">
      <div class="text">
        <h1>우주의 기운을 모아</h1>
        <p><br>열심히하고 광명 찾자!!!<br>모각공부터 일타강사의 웨비나까지<br>협력사 구루미와 함께합니다.</p>
      </div>
    </div>
    <v-container v-if="true">
      <v-card style="width: 80%; margin-top:30px; margin-left: 125px" height="300">
        <v-card-title align="center">🏳‍🌈 현재 메인 웨비나 :<br><b>  "{{room.roomTitle}}" </b></v-card-title>
        <v-card-subtitle ><br>🙋‍♀️강연자: <b>{{room.roomHost}}</b></v-card-subtitle>
        <v-card-text>
          강연시간 : <b>{{DateTime(room.startDate)}}
          ~ {{DateTime(room.endDate)}}</b>
        </v-card-text>
        <v-card-text align="center">
          현재 {{room.roomHost}}님의 웨비나가 메인 등록되었어요~!<br>관심있으신 분들 하단의 버튼을 눌러 접속해주세요!
        </v-card-text>
        <v-card-actions>
          <v-btn
              elevation="2" outlined color="purple" width="120" height="30" v-if="true" style="margin-left: 600px" @click="EntranceGRM(room.roomId)"
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
    }).catch((err)=>{
      console.log(err)
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
.image {
  position:relative;
}

.image .text {
  color: white;
  position:absolute;
  top:80px;
  left:100px;
}
</style>