<template>
  <div class="fill-height box">
    <v-container>
      <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
        <v-form ref="form" @submit.prevent="onSubmitForm">
          <h1 class="text-center">수정하기 🖋</h1>

<!--          <v-select-->
<!--              label="카테고리"-->
<!--              :items="items"-->
<!--              v-model="post.boardType"-->
<!--              dense-->
<!--              outlined-->
<!--              style="width: 145px; height:57px;"-->
<!--          ></v-select>-->
          <v-text-field class="mt-5"
                        height="50" label="제목" outlined
                        v-model="post.title"
                        placeholder="제목을 입력하세요"
          ></v-text-field>
          <v-textarea
              outlined
              v-model="post.content"
              name="input-7-4"
              label="내용"
              placeholder="내용을 입력하세요."
          ></v-textarea>
          <v-card>
            <editor height="500px"/>
          </v-card>
          <v-btn
              color="#AB47BC"
              class="mt-5 float-right mr-3"
              dark
              type="submit"
          >
            수정!!
          </v-btn>
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {updatePost} from "@/apis";

export default {
  name: "BoardModify",
  beforeCreate() {
    let postId = Number(this.$route.params.postId);
    this.$axios.get(`/post/${postId}`).then((response) => {
      console.log(response.data)
      this.post = response.data;
    });
  },
  data(){
    return {
      post: {

      }
    }
  },
  methods: {
    async onSubmitForm(){
      const postData = {
        // post: this.post.postId,
        title: this.post.title,
        content: this.post.content,
      };

      // this.$axios.put(`post/`+ this.post.postId, postData)

      const response = await updatePost(postData, this.post.postId);
      if (response.status == 200) {
        alert(' 정상적으로 수정되었습니다^^');
        await this.$router.push('/boards');
      } else {
        alert(response.data);
      }
    },
  }
}
</script>

<style scoped>

</style>