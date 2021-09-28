<template>
  <v-container style="margin-top: 30px">
    <div>
      <h3> 댓글 ------</h3>

      <div v-for="(item,i) in commentList" :key="item.id">
        <Comments :commentObj="item" :fetchCommentData="fetchCommentData"/>
        <!--      <div v-for="(data) in commentList[i].replyComments" :key="data.id" style="text-indent: 50px">-->
        <!--        <v-divider v-if="i != commentList[i].replyComments.length - 1"></v-divider>-->
        <!--        <Comment :commentObj="data" :fetchCommentData="fetchCommentData"/>-->
        <v-divider v-if="i != commentList.length - 1"></v-divider>
      </div>
      <!--      <v-divider v-if="i != commentList.length - 1"></v-divider>-->
      <!--    </div>-->
      <!--    <Paging  v-if="commentList.length!=0" :totalPage="totalPage" @movePage="movePage" style="margin-bottom: 20px"/>-->
      <CommentWrite :fetchCommentData="fetchCommentData"/>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'
import CommentWrite from "@/components/CommentWrite";
import Comments from "@/components/Comments";
// import Paging from "@/components/Paging";

export default {
  data() {
    return {
      commentList : '',
      // replyCommentList:'',
      // totalPage: null,
      // pageNo: 0
    }
  },
  components: {
    CommentWrite,
    Comments,
    // Paging
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
    },
    // movePage(page) {
    //   this.fetchCommentData(page-1);
    // },



  }

}
</script>

<style scoped>

</style>