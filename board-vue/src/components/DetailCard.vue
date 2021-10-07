<template>
  <div>
    <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
      <div class="col border mt-5">
        <div class="p-5">
          <div class="row">
            <div class="col">
              <div>
                <h2>제목 : {{ post.title }}</h2>
              </div>
              <div>
                <span style="font-weight: bolder; margin:0">작성자: {{ post.nickname }}</span>
                <span style="font-size:small; float: right"> 작성일: {{ post.regDate }}</span><br>
                <span style="font-size:small; float: right"> 조회:  {{ post.viewCount }}</span>
              </div>
            </div>
          </div>
          <hr>
          <div class="row pt-4 pb-5 mb-5 h-100">
            <div class= "col">
              <h3>◾ 내용</h3>
              <p v-html="handleNewLine(post.content)"></p>
            </div>
          </div>

        </div>
      </div>
      <!--    <div style="float: right; margin-top: 10px">-->
      <!--      <v-btn color="#AB47BC" style="margin-right: 7px" v-if= "isLogin===true" >수정</v-btn>-->
      <!--      <v-btn color="#AB47BC" v-if= "isLogin===true" >삭제</v-btn>-->
      <!--    </div>-->
    </v-card>
    <div style="float: right; margin-top: 10px">
      <v-btn
          color="#AB47BC"
          style="margin-right: 7px"
          v-if= "isLogin===true"
          @click = "updatePosting(post.postId)"
      >수정</v-btn>
      <v-btn
          color="#AB47BC"
          v-if= "isLogin===true"
          @click = "deleteFrom(post.postId)"
      >삭제</v-btn>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex"
import {deletePost} from "@/apis";

export default {
  name: "Detail",
  computed: {
    ...mapState(['isLogin'])
  },
  beforeCreate() {
    let postId = Number(this.$route.params.postId);
    this.$axios.get(`/post/${postId}`).then((response) => {
      console.log('123'+response.data)
      console.log(this.$route.params.postId)
      this.post = response.data
    });
  },
  data() {
    return {
      post: [

      ]
    }
  },
  methods: {
    ...mapActions(["getMemberInfo"]),
    updatePosting: function (postId) {
      this.$router.push({
        path: `/boardModify/${postId}`
      })
    },
    //삭제 관련
    async deleteFrom(){
      const response = await deletePost(this.post.postId);
      if (response.status == 200) {
        alert(' 정상적으로 삭제되었습니다^^ ');
        await this.$router.push('/boards');
      }else {
        alert(response.data);
      }
    },
    handleNewLine(str) {
      return String(str).replace(/(?:\r\n|\r|\n)/g,"</br>");
    }
  },

}
</script>

<style scoped>

</style>