<template>
  <v-card flat>
    <template v-if="items.length > 4">
      <v-card-title>
        <v-textarea
            v-model="comment"
            outlined
            label="댓글 작성"
            placeholder="Ctrl + Enter로 작성 가능"
            append-icon="mdi-comment-plus"
            @click:append="save"
            @keypress.ctrl.enter="save"
            hide-details
            auto-grow
            rows="1"
            clearable />
      </v-card-title>
      <v-divider/>
    </template>
    <template v-for="(item, i) in items">
      <v-list-item :key="item.id">
        <v-list-item-icon v-if="isReplay(item)" >
          <v-icon>mdi-subdirectory-arrow-right</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-subtitle v-if="!item.edit" class="black--text white-space">
            <v-icon color="error" left v-if="newCheck(item.updatedAt, 'minutes', 10)">mdi-fire</v-icon> {{item.comment}}
          </v-list-item-subtitle>
          <v-list-item-subtitle v-else>
            <v-textarea
                v-model="item.comment"
                outlined
                label="댓글 수정"
                placeholder="Ctrl + Enter로 작성 가능"
                append-icon="mdi-comment-edit"
                @click:append="update(item)"
                @keypress.ctrl.enter="update(item)"
                hide-details
                auto-grow
                rows="1"
                clearable
                class="mt-2"
            ></v-textarea>
          </v-list-item-subtitle>
          <v-list-item-subtitle class="d-flex justify-end align-center">
            <span class="font-italic mr-4"><display-time :time="item.createdAt"></display-time></span>
            <display-user :user="item.user" size="small"></display-user>
          </v-list-item-subtitle>
          <v-list-item-title class="d-flex justify-end">
            <v-btn
                icon
                @click="item.edit=!item.edit"
                :color="item.edit ? 'warning' : ''"
                v-if="(fireUser && fireUser.uid === item.uid)"
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn
                v-if="fireUser && !isReplay(item)"
                icon
                @click="item.replyEdit=!item.replyEdit"
                :color="item.replyEdit ? 'warning' : ''"
            >
              <v-icon>mdi-comment-multiple</v-icon>
            </v-btn>
            <v-btn color="error" icon @click="remove(item)" v-if="(fireUser && fireUser.uid === item.uid) || (user && user.level === 0)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
            <v-btn @click="like(item)" text>
              <v-icon left :color="liked(item) ? 'success': ''">mdi-thumb-up</v-icon>
              <span>{{item.likeCount}}</span>
            </v-btn>
          </v-list-item-title>
          <v-list-item-subtitle v-if="item.replyEdit">
            <v-textarea
                v-model="item.replyComment"
                @click:append="saveReply(item)"
                @keypress.ctrl.enter="saveReply(item)"
                outlined
                label="대댓글 작정"
                placeholder="Ctrl + Enter로 작성 가능"
                append-icon="mdi-comment-multiple"
                hide-details
                auto-grow
                rows="1"
                clearable
                class="mt-2"></v-textarea>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-divider :key="i"></v-divider>
    </template>
    <v-list-item v-if="lastDoc && items.length < article.commentCount">
      <v-btn
          @click="more"
          :loading="loading"
          v-intersect="onIntersect"
          text
          color="primary"
          block>
        <v-icon>mdi-dots-horizontal</v-icon>더보기
      </v-btn>
    </v-list-item>
    <!-- <v-divider/> -->
    <v-card-title>
      <v-textarea
          v-model="comment"
          outlined
          label="댓글 작성"
          placeholder="Ctrl + Enter로 작성 가능"
          append-icon="mdi-comment-plus"
          @click:append="save"
          @keypress.ctrl.enter="save"
          hide-details
          auto-grow
          rows="1"
          clearable />
    </v-card-title>
  </v-card>
</template>

<script>

export default {
  name: "Comments"
}
</script>

<style scoped>

</style>