<template>
  <div>
    <h1>My page</h1>
    <v-row
        align="right"
        justify="space-around"
    >
      <v-btn depressed>
        회원탈퇴
      </v-btn>
    </v-row>
    <div>
      <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
        <div class="my-4 row">
          <span class="font-weight-black">
            이름
          </span>
          <span v-if="!update" class="ml-4">{{ name }}</span>
          <div v-if="update" class="ml-4">
            <v-text-field
                label="이름을 입력해 주세요 🙌"
                v-model="name"
            ></v-text-field>
          </div>
        </div>
        <div class="my-4 row">
          <span class="font-weight-black">
            email
          </span>
          <span v-if="!update" class="ml-4">{{ email }}</span>
          <span v-if="update" class="ml-4">{{ email }}</span>
<!--          <div v-if="update" class="ml-4">-->
<!--            <v-text-field-->
<!--                v-model="email"-->
<!--                label="이메일을 입력해 주세요:D"-->
<!--            ></v-text-field>-->
<!--          </div>-->
        </div>
        <div>
          <v-btn
              @click="update ? saveMyInfo() : updateBtnHandler()"
              class="white--text"
              color="#8852a6"
              depressed
              elevation="2"
              raised
              rounded
          >{{ update ? "저장" : "정보 수정" }}</v-btn>
        </div>
      </v-card>

      <v-card class="pa-4 ma-4" elevation="2" outlined shaped>
        <div class="my-4 row">
          //내가 쓴 글 보기
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "MyPage",
  data() {
    return {
      update: false,
      name: "로딩 중...",
      email: "로딩 중...",
    };
  },
  methods: {
    updateBtnHandler() {
      this.update = true;
      console.log(this.update);
    },
    saveMyInfo() {
      // api 요청 및 화면 상태 보정
      //

      this.update = false;
    },
    ...mapActions(["getMemberInfo"]),
  },
  async beforeMount() {
    console.log(this.$store.state);
    console.log("beforeCreate >> ", this.getMemberInfo);
    const myInfo = await this.getMemberInfo();
    this.name = myInfo.name ? myInfo.name : "undefined";
    this.email = myInfo.email;
  },
};
</script>

<style scoped>
.row {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
</style>