<template>
  <div>
    <h1>My page</h1>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "MyPage",
  computed: {
    ...mapState(["userInfo"])
  }
}
</script>

<style scoped>

</style>