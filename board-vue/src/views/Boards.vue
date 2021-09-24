<template>
  <v-container>
    <v-card >
      <v-card-title>
        📝 공부게시판
        <v-spacer></v-spacer>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
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
          height="300px"
      >
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              #NO
            </th>
            <th class="text-left">
              제목
            </th>
            <th class="text-left">
              작성자
            </th>
            <th class="text-left">
              작성일자
            </th>
            <th class="text-left">
              조회수
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in datas"
              :key="item.name"
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
      <div class="text-center">
        <v-pagination
            v-model="page"
            :length="4"
            circle
        ></v-pagination>
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
          @click="addPost">

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
      datas:[

      ]

    };
  },

  created () {
    this.postList(1)
  },
  methods: {
    postList(categoryId) {
      this.$axios.get(`board/list/${categoryId}`)
          .then((res) => {
            this.datas = res.data.content;
          })
          .then((err) => {
            console.log(err);
          })
    },

    detailPosting: function (postId) {
      this.$router.push({
        path: `/board/${postId}`
      });
    },

    addPost: function () {
      this.$router.push({
        path: `/create`
      })
    }
  }
}
</script>

<style scoped>

</style>