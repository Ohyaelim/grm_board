<template>
  <div>
    <h1>My page</h1>

    <div>
      <v-card class="pa-4 ma-4" v-model="valid" elevation="2" outlined shaped>
        <div class="my-4 row">
          <span class="font-weight-black">
            이름
          </span>
          <span v-if="!update" class="ml-4">{{ infoData.nickname }}</span>
          <div v-if="update" class="ml-4">
            <v-text-field
                label="이름을 입력해 주세요 🙌"
                :rules="nameRules"
                v-model="infoData.nickname"
            ></v-text-field>
          </div>
        </div>
        <div class="my-4 row">
          <span class="font-weight-black">
            email
          </span>
          <span v-if="!update" class="ml-4">{{ infoData.email }}</span>
          <span v-if="update" class="ml-4">{{ infoData.email }}</span>

        </div>
<!--        <div class="my-4 row">-->
<!--          <span class="font-weight-black">-->
<!--            Role-->
<!--          </span>-->
<!--          <span v-if="!update" class="ml-4">{{ infoData.nickname }}</span>-->
<!--          <div v-if="update" class="ml-4">-->
<!--            <v-text-field-->
<!--                label="ROLE 변경 🙌"-->
<!--                v-model="infoData.nickname"-->
<!--            ></v-text-field>-->
<!--          </div>-->
<!--        </div>-->
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
      <v-row
          align="center"
          justify="space-around"
      >
        <v-btn
            class="mb-5"
            elevation="2"
            raised
            rounded
            color="#536DF"
            width="300px"
            @click="goMyBoard(infoData.memberId)"
        >내가 쓴 글 보기</v-btn>


      </v-row>

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
import {updateMyPage} from "@/apis";

export default {
  name: "MyPage",
  data() {
    return {
      valid:false,
      infoData: {
        nickname: "로딩 중...",
        email: "로딩 중..",
        memberId: '',
        // role: ''
      },

      dialog: false,
      update: false,
      nameRules:[
        v => !!v || '닉네임은 필수입니다.',
        v => /[ㄱ-ㅎㅏ-ㅣ가-힣]/.test(v) || '닉네임은 한글만 입력하세요',
        v => (v.length<101) || '닉네임은 100자 이하만 입력하세요.',
        v => (v.length>1) || '닉네임은 한글자 이상 입력하세요.',
      ]
    };
  },
  methods: {
    updateBtnHandler() {
      this.update = true;
      console.log(this.update);
    },
    saveMyInfo() {
      // api 요청 및 화면 상태 보정
      updateMyPage(this.infoData.nickname);
      this.update = false;
    },
    ...mapActions(["getMemberInfo"]),

    goMyBoard(memberId) {
      this.$router.push({
        path: `/mypage/myBoards/`+ memberId
      });

    },

  },
  async beforeMount() {
    console.log(this.$store.state);
    console.log("beforeCreate >> ", this.getMemberInfo);

    const myInfo = await this.getMemberInfo();
    this.infoData.nickname = myInfo.nickname ? myInfo.nickname : "다시 로그인 해주세요 😉";
    this.infoData.email = myInfo.email;
    this.infoData.memberId = myInfo.memberId;
    // this.infoData.role = myInfo.role;
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