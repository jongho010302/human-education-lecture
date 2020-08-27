<template>
  <div>
    <v-list>
      <v-list-item>
        <v-img :src="feed.profile_img" max-width="30" max-height="30" class="mr-1" style="border-radius: 50%;"></v-img>
        <span class="body-1">{{feed.profile_short_name}}: {{comment.content}}</span>
      </v-list-item>
      <v-divider></v-divider>
    </v-list>
  </div>
</template>

<script>
  export default {
    name: 'component-item',
    props: ["feed", "comment"],
  }
</script>