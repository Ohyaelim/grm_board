<template>
  <div class="ma-3" style="height: 300px" @submit.prevent="onSubminForm">

    <v-textarea
        outlined
        clearable
        clear-icon="mdi-close"
        placeholder="댓글을 입력하세요."
        v-model="content"
        rows="4"
    />
    <div class="text-right">
      <v-btn outlined type="submit">댓글 등록</v-btn>
    </div>
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
      form:{
        comments: ''
      },
    }
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    async onSubminForm(){
      const commentData={
        comments: this.form.comments
      };
      const response = await createComment(commentData);
      if (response.status == 200){
        console.log(response.data)
        await this.$router.push(`/boardDetail/${response.data}`);
      }else {
        alert(response.data);
      }
    }

  }

}
</script>

<style scoped>

</style>