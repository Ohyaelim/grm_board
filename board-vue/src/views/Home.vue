<template>
  <div class="fill-height">
    <v-row align="center" class="fill-height" justify="center">
      <h1 v-if="isLogin" class="display-2">Welcome 🙌 </h1>
      <h2 v-else class="display-2">➰ 로그인 또는 회원가입 후 이용해주세요 ➰</h2>
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
}
</script>

<style scoped>

</style>