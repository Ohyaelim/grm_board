<template>
<!--  <div class="mt-lg-5">-->
<!--    <b-table striped hover :items="datas" :fields="fields" @row-clicked="detailPosting(datas.)"></b-table>-->
<!--  </div>-->
  <div id="app">
    <table class="table table-hover table-striped">
      <thead class="thead-dark">
      <tr>
        <th>#No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="ab in datas" :key="ab.postId"
      @click = "detailPosting(ab.postId)">
      <td><a href="">{{ab.postId}}</a></td>
      <td>{{ab.title}}</td>
      <td>{{ab.name}}</td>
      <td>{{ab.viewsCount}}</td>
      <td>{{ab.commentsCount}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fields:[
        {
         key: 'post_id',
        label: '글 번호'
        },
        {
          key: 'title',
          label: '제목'
        },
        {
          key: 'name',
          label: '작성자'
        },
        {
          key: 'viewsCount',
          label: '조회수'
        },
        {
          key: 'commentsCount',
          label: '작성일자'
        },],
     datas: []

    };
  },

  beforeMount () {
    this.postList()
  },
  methods: {
    postList() {
      this.$axios.get("posts/1")
          .then((res) => {
            console.log(res.data);
            this.datas = res.data.content;
          })
          .then((err) => {
            console.log(err);
          })
    },

    detailPosting: function (post_id) {
      this.$router.push({
        path: `/posts/${post_id}`
      });
    },

  }
}
</script>

<style>
/*#list{*/
/*  margin: 100px 50px 50px;*/
/*}*/
</style>