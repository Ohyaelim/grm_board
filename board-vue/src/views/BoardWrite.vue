<template>
  <div class="fill-height box">
    <v-container>
      <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
        <v-form ref="form" v-model="valid" @submit.prevent="onSubmitForm">
          <h1 class="text-center">글쓰기 🖋</h1>

          <v-select
              label="카테고리"
              :items="items"
              v-model="form.board"
              :rules="categoryRules"
              dense
              outlined
              style="width: 145px; height:57px;"
          ></v-select>
          <v-text-field class="mt-5"
                        height="50" label="제목" outlined
                        v-model="form.title"
                        :rules="titleRules"
                        placeholder="제목을 입력하세요"
          ></v-text-field>
          <v-textarea
              outlined
              v-model="form.content"
              :rules="contentRules"
              label="내용"
              placeholder="내용을 입력하세요."
          ></v-textarea>
          <v-card>
            <editor height="500px"/>
          </v-card>
          <v-btn color="#AB47BC" type="submit" :disabled="!valid">글쓰기</v-btn>
        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import {createPost} from "@/apis";

export default {
  data() {
    return {
      valid:false,
      items: [
        {text: '공부게시판', value: 1}
      ],
      form: {
        title: '',
        content: '',
        board: null,
      },
      categoryRules:[
          v=>!!v || '반드시 선택해주세요'
      ],
      titleRules: [
          v => !!v || '제목은 필수입니다.',
          v => (v.length<100) || '제목을 100자 미만으로 적어주세요.'
      ],
      contentRules: [
          v => !!v || '내용은 필수입니다.',
          v => (v.length<1000) || ' 내용이 너무 길어요~!'
      ]
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