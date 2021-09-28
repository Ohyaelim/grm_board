<template>
  <div>
    <div>
      <v-container>
        <Detail/>
        <!--    <Comments/>-->
      </v-container>
    </div>
        <CommentList/>
  </div>
</template>

<script>
// import Comments from "@/components/Comments";
import CommentList from "@/components/CommentList";
import Detail from "@/components/DetailCard";

// import {mapState} from "vuex"

export default {
  name: "BoardDetail",
  components: {
    CommentList,
    Detail,
    // Comments,

  }
}
</script>

<style scoped>

</style>