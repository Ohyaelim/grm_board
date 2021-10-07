<template>
  <v-container>
    <v-card STYLE="margin-top: 100">
      <v-card-title>
        📝 공부게시판
        <v-spacer></v-spacer>
        <v-text-field
            append-icon="mdi-magnify"
            label="아직 검색사용이 불가합니다. (빠른 시일 내에 도입예정 🙃)"
            single-line
            hide-details
        ></v-text-field>
      </v-card-title>
      <!--      <v-data-table-->
      <!--          :headers="headers"-->
      <!--          :items="desserts"-->
      <!--          :search="search"-->
      <!--      ></v-data-table>-->
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
              v-for="item in datas.content"
              :key="item.postId"
              @click = "detailPosting(item.postId)"
          >
            <td v-if = "!item.isDeleted">{{ item.postId }}</td>
            <td v-if = "!item.isDeleted">{{ item.title }}</td>
            <td v-if = "!item.isDeleted">{{ item.nickname }}</td>
            <td v-if = "!item.isDeleted">{{ item.viewCount }}</td>
            <td v-if = "!item.isDeleted">{{ item.regDate }}</td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
      <div class="text-center">
        <v-pagination
            v-model="currentPage"
            :length="this.datas.totalPages"
            color="#1A237E"
            @input="handlePageChange"
            circle>
        </v-pagination>
      </div>
    </v-card>
    <v-col class="text-right">
      <v-btn
          v-if= "isLogin===true"
          align="right"
          class="ma-1"
          outlined
          small
          fab
          color="#8852a6"
          to="/boardWrite"
      >

        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </v-col>
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
    }
  },

  created () {
    this.postList(1)
  },
  methods: {
    postList(boardId) {
      this.$axios.get(`post/list/${boardId}`)
          .then((res) => {
            this.datas = res.data;
            console.log(res.data)
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
    computed: {
      rows() {
        return this.datas.length
      }
    },
    handlePageChange(value) {
      this.currentPage=value
      this.$axios.get(`post/list/1`+'?page='+(value-1))
          .then((res) => {
            this.datas = res.data;
            console.log(res.data)
          })
          .then((err) => {
            console.log(err);
          })
    },
  }
}
</script>

<style scoped>

</style>