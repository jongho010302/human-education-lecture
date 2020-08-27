<template>
  <div>
    <!-- Feed Content -->
    <v-card class="mb-1">
      <v-container>
        <v-card-title>
          <v-img :src="feed.profile_img" max-width="45" max-height="45" class="mr-4" style="border-radius: 50%;"></v-img>
          <div>
            <h5>{{feed.profile_short_name}}</h5>
            <div class="font-weight-light caption">{{feed.profile_full_name}}</div>  
          </div>
          <div class="flex-grow-1"></div>
          <v-menu class="float-right">
            <template v-slot:activator="{ on }" >
              <v-btn icon v-on="on">
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="(menu, index) in menus" :key="index" @click="del(menu)">
                <v-list-item-title>{{menu.item}}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-card-title>
      </v-container>
      <v-divider></v-divider>
      <v-card-text class="ml-3">
        <v-icon size="15">mdi-alarm</v-icon>
        <timeago :datetime="feed.timeStamp" :auto-update="10" :converterOptions="{includeSeconds: true}"></timeago>
        <h1 class="mt-2 mb-4 text--primary" > {{feed.title}}</h1>
        <h4 class="mb-7">{{feed.content}}</h4>
        <v-img :src="feed.attached_img" :alt="feed.content"></v-img>
        <v-divider></v-divider>
        <v-icon class="mr-1" size=18 color="red" v-if="like" @click="like = false" >fas fa-heart</v-icon>
        <v-icon class="mr-1" size=18 color="black" v-else @click="like = true">far fa-heart</v-icon>
        <span v-if="like">Liked</span>
        <span v-else>Like</span>
        <v-icon size=19 color="black" class="ml-3" @click="comment_class = (comment_class === 'mb-12') ? 'mb-12 d-none' : 'mb-12' ">far fa-comment</v-icon> Comment
        <v-icon size=19 color="black" class="ml-1">far fa-share-square</v-icon> Share
      </v-card-text>
    </v-card>
    <!-- Comment Component -->
    <comment-items :feed="feed" :class="comment_class"></comment-items>
    <!-- Edit Dialog -->
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>Edit Feed</v-card-title>
        <v-card-text class="pb-0">Type what you what you want to edit</v-card-text>
        <v-card-actions>
          <v-card-text class="pb-0">
            <v-textarea v-model="editText" counter outlined maxlength="240" label="Edit text"></v-textarea>
            <v-file-input v-model="file" accept="image/png, image/jpeg, image/bmp" placeholder="Pick an Image" prepend-icon="mdi-camera" label="Image"></v-file-input>
          </v-card-text>          
        </v-card-actions>
        <v-container>
          <div class="float-right">
            <v-btn class="mr-2" color="blue" @click="edit">Edit</v-btn>
            <v-btn color="green" @click="dialog = false; editText=''">Close</v-btn>
          </div>
          <div style="clear: both;"></div>
        </v-container>
      </v-card>
    </v-dialog>    
  </div>
</template>

<script>
  import CommentItems from '../components/CommentItems';

  export default {
    name: 'feed-item',
    components: {
      CommentItems,
    },
    props: ["feed"],
    data() {
      return {
        file: null,
        like: false,
        menus: [
          {item: 'Edit'},
          {item: 'Delete'}  
        ],
        dialog: false,
        editText: '',
        comment_class: 'mb-12 d-none'
      }
    },
    methods: {
      // Delete feed
      del(menu) {
        if(menu.item === 'Edit') {
          this.dialog = true;
        } 
        else if (menu.item === 'Delete') {
          this.$emit('emitDel');
        }
      },
      // Edit Feed
      edit() {
        if(this.file){
          var reader = new FileReader();
          reader.readAsDataURL(this.file);
          reader.onload =  () => {
            this.edit_(reader.result);
          };
        } else {
          this.edit_();
        }
      },
      edit_(fileString) {
        this.feed.title = this.editText;
        this.feed.content = this.editText;
        this.feed.attached_img = fileString;

        this.editText = '';
        this.dialog = false;
        this.file = null;
      },
    },
  }
</script>