<template>
  <div>
    <div class="row pt-4 pb-5 mb-5" style="background-color: #0d47a1; height: 300px; align-items: center">
      <h1>하이</h1>
    </div>
    <v-container>
      <v-card style="width: 30%" height="300">
        <v-card-title>{{room.roomTitle}}</v-card-title>
        <v-card-subtitle>{{room.roomId}}}</v-card-subtitle>
        <v-card-text>
          {{room.startDate}}
        </v-card-text>
        <v-card-text>
          Vuetify를 공부 중 입니다.
        </v-card-text>
        <v-card-actions>
          <v-btn outlined id="click">Click</v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {mapState} from "vuex"

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