<template>
  <div>
    <v-card class="ma-3" style="border: none" outlined min-height="20">
      <v-card-title class="text-left">

        <span class="text-body-1 mr-2">{{commentObj.email}}</span>
        <span style="font-size: small" >{{commentObj.createdAt}}</span>
        <v-btn fab text x-small v-if="getUserName() === commentObj.email" @click="comment_modify(modifyCheck)">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn fab text x-small v-if="getUserName() === commentObj.email" @click="comment_delete(commentObj.id)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text class="text-left">

        <span v-if="!modifyCheck">
<!--          <span v-if="commentObj.rootNickname!=null">@{{commentObj.rootNickname}}</span>-->
          {{commentObj.comments}}
<!--          <v-btn fab text x-small  @click="commentReply = !commentReply">-->
<!--            <v-icon>💬</v-icon>-->
<!--          </v-btn>-->
<!--             <ReplyCommentWrite  v-if="commentReply" :commentStatus="commentReply" :rootId="commentObj.commentsId" :fetchCommentData="fetchCommentData"/>-->
        </span>

        <span v-else>
          <v-text-field
              v-model="commentObj.comments"
              dense
              outlined
          ></v-text-field>
        </span>
      </v-card-text>
    </v-card>

  </div>

</template>

<script>
import axios from "axios";
// import ReplyCommentWrite from "@/pages/replyCommentWrite";

export default {
  props: {
    commentObj: Object,
    fetchCommentData: Function

  },
  data() {
    return{
      modifyCheck: false,
      commentReply:false
    }
  },
  components: {
    // ReplyCommentWrite,
  },
  methods: {
    getUserName() {
      return this.$store.getters.getUserName
    },
    // comment_reply(){
    //   this.commentReply=true
    // },
    comment_modify(modifyCheck) {
      if(modifyCheck) {
        if(this.$store.getters.getUserName != null && this.$store.getters.getUserName != '') {
          if(this.commentObj.contents.length > 0) {
            axios.post('/comment/modify/' + this.$route.params.postId,
                this.commentObj
            ).then((res) => {
              if(res.data.result != 1) {
                window.alert(res.data.msg)
              }
              this.fetchCommentData()
            }).catch((err) => {
              window.alert(err)
            })
          }else {
            window.alert("내용을 입력해주세요.")
          }
        }else {
          window.alert("작성자만 댓글을 수정할 수 있습니다.")
        }
      }
      this.modifyCheck = !modifyCheck


    },
    comment_delete(commentNo) {
      if(window.confirm('댓글을 삭제하시겠습니까?')) {
        axios.get('/comment/delete/'+this.$route.params.postId+'/'+commentNo)
            .then((res) => {
              window.alert(res.data.msg)
              this.fetchCommentData();
            }).catch((err) => {
          window.alert(err)
          window.console.log(err)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>