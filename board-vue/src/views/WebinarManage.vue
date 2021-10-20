<template>
  <div align="right">
    <v-btn
        :loading="loading3"
        :disabled="loading3"
        color="#8852a6"
        class="ma-2 white--text"
        @click="loader = 'loading3'"
        to="/webinar/create"
    >
      심포지엄 등록
      <v-icon
          right
          dark
      >
        mdi-cloud-upload
      </v-icon>
    </v-btn>

    <v-container>
      <v-card STYLE="margin-top: 100">
        <v-card-title>
          심포지엄 목록
          <v-spacer></v-spacer>
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
                강연자
              </th>
              <th class="text-left">
                주제
              </th>
              <th class="text-left">
                일시
              </th>
              <th class="text-left">
                관리
              </th>
              <th class="text-left">
                신청자목록
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
      </v-card>
    </v-container>
  </div>

</template>

<script>
export default {
  name: "WebinarManage"
}
</script>

<style scoped>

</style>