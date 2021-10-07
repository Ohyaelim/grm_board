<template>
  <v-container>
    <v-card >
      <v-card-title>
        📝 내가 쓴 글!
        <v-spacer></v-spacer>
      </v-card-title>
      <v-simple-table
          fixed-header
          height="390px"
      >
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              No.
            </th>
            <th class="text-left">
              제목
            </th>
            <th class="text-left">
              조회수
            </th>
            <th class="text-left">
              최근수정일자
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in datas"
              :key="item.postId"
              @click = "detailPosting(item.postId)"
          >
            <td v-if = "!item.isDeleted">{{ item.postId }}</td>
            <td v-if = "!item.isDeleted">{{ item.title }}</td>
            <td v-if = "!item.isDeleted">{{ item.viewCount }}</td>
            <td v-if = "!item.isDeleted">{{ item.regDate }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card>
  </v-container>
</template>
<script>
import {mapActions, mapState} from "vuex"
export default {
  name: "Boards",
  computed: {
    ...mapState(['isLogin'])
  },
  data() {
    return {
      currentPage: 1,
      datas:[
      ],
      infoData:{
        memberId: ''
      }
    };
  },
  async created() {
    const myInfo = await this.getMemberInfo();
    this.infoData.memberId = myInfo.memberId;
    this.postList()
  },
  methods: {
    postList() {
      this.$axios.get(`/post/list/mypage/`+this.$route.params.memberId)
          .then((res) => {
            console.log(res.data)
            this.datas = res.data.content;
          })
          .then((err) => {
            console.log(err);
          })
    },
    detailPosting: function (postId) {
      this.$router.push({
        path: `/boardDetail/${postId}`
      });
    },
    ...mapActions(["getMemberInfo"]),
  },

}
</script>