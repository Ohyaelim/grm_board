<template>
  <v-container>
    <div class="col border mt-5">
      <div class="p-5">
        <div class="row">
          <div class="col">
            <div>
              <h2>detail</h2>
            </div>
            <div>
              <span style="font-weight: bolder; margin:0">작성자: </span>
              <span style="font-size:small; float: right"> 작성일: </span><br>
              <span style="font-size:small; float: right"> 조회:  </span>
            </div>
          </div>
        </div>
        <hr>
        <div class="row pt-4 pb-5 mb-5 h-100">
          <div class= "col">
            <p>내용</p>
          </div>
        </div>

      </div>
    </div>
    <div style="float: right; margin-top: 10px">
      <v-btn color="#AB47BC" style="margin-right: 7px">수정</v-btn>
      <v-btn color="#AB47BC">삭제</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "BoardDetail"
}
</script>

<style scoped>

</style>