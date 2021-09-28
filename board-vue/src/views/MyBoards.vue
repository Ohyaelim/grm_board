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
              작성자
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
            <td>{{ item.postId }}</td>
            <td>{{ item.title }}</td>
            <td>{{ item.nickname }}</td>
            <td>{{ item.viewCount }}</td>
            <td>{{ item.regDate }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card>
  </v-container>
</template>
<script>
import {mapState} from "vuex"
export default {
  name: "Boards",
  computed: {
    ...mapState(['isLogin'])
  },
  data() {
    return {
      currentPage: 1,
      datas:[
      ]
    };
  },
  created() {// TODO: 멤버아이디 갖구와
   this.postList(1)
  },
  methods: {
    postList(memberId) {
      this.$axios.get(`/post/list/mypage/${memberId}`)
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
    }
  }
}
</script>