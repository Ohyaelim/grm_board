<template>
  <div class="fill-height">
    <v-row align="center" class="fill-height" justify="center">
      <div>
        <h1 v-if="isLogin" class="display-2">Welcome 🙌</h1>
        <h2 v-else class="display-2">➰ 로그인 또는 회원가입 후 이용해주세요 ➰</h2>
      </div>
      <div>
        <v-app id="inspire">
          <br>
          <v-card
              style="padding-top: 10px"
              class="mx-auto"
              max-width="400"
          >
            <v-img
                class="white--text align-end"
                height="200px"
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-card-title>Top 10 Australian beaches</v-card-title>
            </v-img>

            <v-card-subtitle class="pb-0">
              Number 10
            </v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitehaven Beach</div>

              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn
                  color="orange"
                  text
              >
                Share
              </v-btn>

              <v-btn
                  color="orange"
                  text
              >
                Explore
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-app>
      </div>
    </v-row>
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
  }
}
</script>

<style scoped>

</style>