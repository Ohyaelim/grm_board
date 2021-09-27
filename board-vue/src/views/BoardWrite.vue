<template>
  <div class="fill-height box">
    <v-container>
      <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
        <v-form ref="form" @submit.prevent="onSubmitForm">
          <h1 class="text-center">글쓰기 🖋</h1>

          <v-select
              label="카테고리"
              :items="items"
              v-model="form.board"
              dense
              outlined
              style="width: 145px; height:57px;"
          ></v-select>
          <v-text-field class="mt-5"
                        height="50" label="제목" outlined
                        v-model="form.title"
                        placeholder="제목을 입력하세요"
          ></v-text-field>
          <v-textarea
              outlined
              v-model="form.content"
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
            글쓰기
          </v-btn>
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {createPost} from "@/apis";

export default {
  name: "BoardWrite",
  data() {
    return {
      items: [
        {text: '공지사항', value: 1},
        {text: '공부게시판', value: 2},
        {text: '질문게시판', value: 3},
      ],
      form: {
        title: '',
        content: '',
        board: null,
      },
    }
  },
  methods:{
    async onSubmitForm() {
      const postData = {
        title: this.form.title,
        content: this.form.content,
        board: this.form.board
      };
      const response = await createPost(postData);
      if (response.status == 200) {
        alert(' 정상적으로 등록되었습니다^^');
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