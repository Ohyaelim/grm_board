<template>
  <div>
    <div>
      <v-container>
        <Detail/>
        <!--    <Comments/>-->
      </v-container>
    </div>
    <div>
      <v-container>
<!--        <Comments/>-->
      </v-container>
    </div>
  </div>
</template>

<script>
// import Comments from "@/components/Comments";
import Detail from "@/components/DetailCard";
// import {mapState} from "vuex"

export default {
  name: "BoardDetail",
  components: {
    Detail,
    // Comments
  }
}
</script>

<style scoped>

</style>