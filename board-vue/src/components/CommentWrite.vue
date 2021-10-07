<template>
  <div class="ma-3"  style="height: 300px" >
    <v-container v-model="valid">

      <v-textarea
          outlined
          clearable
          clear-icon="mdi-close"
          placeholder="댓글을 입력하세요."
          v-model="form.comments"
          rows="4"
          :rules="commentRules"
      />
      <div class="text-right">
        <v-btn outlined :disabled="!valid" @click="onSubmitForm">댓글 등록</v-btn>
      </div>
    </v-container>

  </div>
</template>

<script>
// import axios from "axios";
// import store from "@/store";
import {mapState} from "vuex"
import {createComment} from "@/apis";

export default {
  props: {
    fetchCommentData: Function
  },
  data(){
    return{
      valid:false,
      form:{
        comments: ''
      },
      commentRules: [
          v => !!v || '댓글을 쓰고 싶으시다면 작성해주세요',
          v => (v.length<254) || '엇! 너무 길어요~ 줄여주세요'
      ]
    }
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    async onSubmitForm(){
      const commentData={
        comments: this.form.comments
      };
      const response = await createComment(commentData, this.$route.params.postId);
      if (response.status == 200){
        console.log(response.data)
        // await this.$router.push(`/boardDetail/${this.$route.params.postId}`);
      }else {
        alert(response.data);
      }
      this.fetchCommentData()
    }

  }

}
</script>

<style scoped>

</style>