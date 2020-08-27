<template>
  <div>
    <v-card>
      <v-card-title>Comments</v-card-title>
      <v-card-text>
        <comment-item :feed="feed" :comment="comment" v-for="(comment, index) in feed.comments" :key="index"/>
        <v-textarea v-model="commentText" rows="1" label="Leave a comment" prepend-icon="far fa-comment"></v-textarea>
        <v-btn class="float-right" outlined color="blue" @click="leaveComment">Comment</v-btn>
        <div style="clear: both;"></div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
  import CommentItem from '../components/CommentItem';

  export default {
    name: 'comment-items',
    props: ["feed"],
    components: {
      CommentItem,
    },
    
    data() {
      return {
        commentText: '',
        comments: [],
      }
    },

    methods: {
      leaveComment() {
        this.feed.comments.push({ content: this.commentText });
        this.commentText = '';
      },
    }
  }
</script>