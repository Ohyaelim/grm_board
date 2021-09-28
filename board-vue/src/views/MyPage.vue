<template>
  <div>
    <h1>My page</h1>

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

          <v-btn
              class="mb-5"
              elevation="2"
              raised
              rounded
              color="#536DF"
              width="300px"
              to="/mypage/myBoards"
          >내가 쓴 글 보기</v-btn>
        </div>
      </v-card>
    </div>
    <v-row justify="center">
      <v-dialog
          v-model="dialog"
          persistent
          max-width="290"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
              depressed
              v-bind="attrs"
              v-on="on"
          >
            G.S.W.M 탈퇴
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="text-h5">
            정말 탈퇴하실 건가요?
          </v-card-title>
          <v-card-text align="center"><br>저희와 열심히 공부해요... <br>그래도 탈퇴를 원하시면 <a href="mailto:yaelim1122@gooroomee.com">yaelim1122@gooroomee.com</a>으로
            <br>메일을 보내주세요.<br>탈퇴처리 도와드리겠습니다.😢</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
                color="#8852a6"
                text
                @click="dialog = false"
            >
              OK
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from "vuex";
// import {registerUser} from "@/apis";
// import {updateMyPage} from "@/apis";

export default {
  name: "MyPage",
  data() {
    return {
      dialog: false,
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
      // const response = await registerUser(userData);
      // console.log(response)
      // if (response.status == 200) {
      //   alert('환영합니다.');
      //   await this.$router.push('/login');
      // } else {
      //   alert(response.data);
      // }
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