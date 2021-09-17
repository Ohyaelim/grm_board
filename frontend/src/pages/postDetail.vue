<template>
  <div>
    <b-card>
      <b-card-title>제목 : {{detail.title}}</b-card-title>
      <div class="d-flex justify-space-between mx-5">
        <b-card-sub-title class="pa-0">
          작성자 : {{detail.nickname}}
        </b-card-sub-title>
        <b-card-sub-title class="pa-0">
          작성일자 : {{detail.regDate}}
        </b-card-sub-title>
      </div>
      <b-card-text>
        {{detail.contents}}
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "PostDetail",
  created() {
    let postId = Number(this.$route.params.postId);
    this.$axios.get(`/board/${postId}`).then((response) => {
      console.log(response.data)
      this.detail = response.data;

    });
  },
  data() {
    return {
      detail: {},
    };
  },

  methods: {
  }
};
</script>
<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}
.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}
.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}
.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}
.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
.content-detail-comment {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
</style>