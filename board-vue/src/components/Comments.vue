<template>
  <div>
    <v-card class="ma-3" style="border: none" outlined min-height="20">
      <v-card-title class="text-left">

        <span class="text-body-1 mr-2">{{commentObj.nickname}}</span>
        <span style="font-size: small" >{{commentObj.regDate}}</span>
        <v-btn fab text x-small v-if="getUserName() === commentObj.nickname" @click="comment_modify(modifyCheck)">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn fab text x-small v-if="getUserName() === commentObj.nickname" @click="comment_delete(commentObj.id)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text class="text-left">

        <span v-if="!modifyCheck">
          <span v-if="commentObj.rootNickname!=null">@{{commentObj.rootNickname}}</span>
          {{commentObj.contents}}
          <v-btn fab text x-small  @click="commentReply = !commentReply">
            <v-icon>💬</v-icon>
          </v-btn>
             <ReplyCommentWrite  v-if="commentReply" :commentStatus="commentReply" :rootId="commentObj.commentsId" :fetchCommentData="fetchCommentData"/>
        </span>

        <span v-else>
          <v-text-field
              v-model="commentObj.contents"
              dense
              outlined
          ></v-text-field>
        </span>
      </v-card-text>
    </v-card>

  </div>
</template>

<script>

export default {
  name: "Comments"
}
</script>

<style scoped>

</style>