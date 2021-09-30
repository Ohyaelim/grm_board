<template>
  <v-container style="margin-top: 30px">
    <div>
      <br/>
      <h3>  🙋‍♀️ 댓글 ------</h3>

      <div v-for="(item,i) in commentList" :key="item.id">
        <Comments :commentObj="item" :fetchCommentData="fetchCommentData"/>

        <v-divider v-if="i != commentList.length - 1"></v-divider>
      </div>
      <CommentWrite :fetchCommentData="fetchCommentData"/>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'
import CommentWrite from "@/components/CommentWrite";
import Comments from "@/components/Comments";

export default {
  data() {
    return {
      commentList : '',
    }
  },
  components: {
    CommentWrite,
    Comments,
  },
  created() {
    this.fetchCommentData()
  },
  methods : {

    fetchCommentData() {

      axios.get('/comments/' + this.$route.params.postId)
          .then((res) => {
            this.commentList = res.data
            // this.totalPage = res.data.totalPages
            console.log(this.commentList)



          }).catch((err) => {
        window.alert(err)
      })
    }

  }

}
</script>

<style scoped>

</style>