<template>
  <div>
    <v-container style="background-color: #ECF0F1;">
      <v-row>
        <!-- Left side content -->
        <v-col cols="12" md="3">
          <v-card>
            <v-card-title class="pb-1">@LeeCross</v-card-title>
            <v-card-text class="pb-3">
              Fullname : Miracles Lee Cross
              <div class="text--primary">
                Developer of web applications, JavaScript, PHP, Java, Python, Ruby, Java, Node.js, etc
              </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-list-item>
              <v-list-item-content class="pb-0">
                <v-list-item-subtitle>Followers</v-list-item-subtitle>
                <v-list-item-content>{{followers}}</v-list-item-content>
              </v-list-item-content>
            </v-list-item>
            <v-list-item two-line>
              <v-list-item-content>
                <v-list-item-subtitle>Following</v-list-item-subtitle>
                <v-list-item-content>{{following}}</v-list-item-content>
              </v-list-item-content>
            </v-list-item>
          </v-card>
        </v-col>
        <!-- Center side content -->
        <v-col cols="12" md="6">
          <v-card>
            <v-tabs background-color="deep-purple accent-4" center-active dark>
              <v-tab>Make a publication</v-tab>
              <v-tab>Images</v-tab>
              <v-tab-item>
                <v-card class="mb-8">
                  <v-card-text class="pb-0">
                    <v-textarea v-model="post" counter outlined maxlength="240" label="What are you thinking?"></v-textarea>
                  </v-card-text>
                  <v-container><v-btn outlined color="blue" @click="onUpload">share</v-btn></v-container>
                </v-card>
              </v-tab-item>
              <v-tab-item>
                <v-card class="mb-8">
                  <v-card-text class="pb-0" style="width: 1">
                    <v-textarea v-model="post" counter outlined maxlength="120"  label="What are you thinking?"> </v-textarea>
                    <v-file-input v-model="file" accept="image/png, image/jpeg, image/bmp" placeholder="Pick an Image" prepend-icon="mdi-camera" label="Image"></v-file-input>                      
                  </v-card-text>
                  <v-container><v-btn outlined color="blue" @click="onUpload">share</v-btn></v-container>
                </v-card>
              </v-tab-item>
            </v-tabs>
          </v-card>
          <feed-item :feed="feed" v-for="(feed, index) in feeds" :key="index" @emitDel="checkForm(index)"></feed-item>
        </v-col>
        <!-- Right side content -->
        <v-col cols="12" md="3">
          <v-card class="mb-12">
            <v-card-title class="pb-1">Card title</v-card-title>
            <v-card-text class="pb-3">
              Card subtitle
              <div class="text--primary">
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </div>
            </v-card-text>
            <v-container><div class="ml-4">Card link &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Another link</div></v-container>
          </v-card> 
          <v-card>
            <v-card-title class="pb-1">Card title</v-card-title>
            <v-card-text class="pb-3">
              Card subtitle
              <div class="text--primary">
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </div>
            </v-card-text>
            <v-container><div class="ml-4">Card link &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Another link</div></v-container>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <!-- Delete validation dialog-->
    <validate-form v-if="validate" @validateYes="delFeed" @validateNo="validate = false"></validate-form>
  </div>
</template>

<script>
  import FeedItem from '../components/FeedItem'
  import ValidateForm from '../components/ValidateForm'

  export default {
    name: 'feeds',
    components: {
      FeedItem,
      ValidateForm,
    },
    data() {
      return {
        profile_short_name: '@LeeCross',
        profile_full_name: 'Miracles Lee Cross',
        profile_img: 'https://picsum.photos/45/45',        
        followers: null,
        following: null,
        post: '',
        file: null,
        feeds: [],
        validate: false,
        tempIndex: 0,
      }
    },
    created() {
      this.followers = "52,342";
      this.following = "6,758";
    },
    methods: {
      // Upload
      onUpload(){
        if(this.file){
          var reader = new FileReader();
          reader.readAsDataURL(this.file);
          reader.onload = () => {
            this.addFeed(reader.result);
          };
        } else {
          this.addFeed();
        }
      },
      // Add Feed
      addFeed(fileString) {
        this.feeds.unshift({
          profile_img: this.profile_img,
          profile_short_name: this.profile_short_name,
          profile_full_name: this.profile_full_name,
          timeStamp: new Date().getTime(),
          title: this.post,
          content: this.post,
          attached_img: fileString,
          comments: [],
        });
        this.post = '';
        this.file = null;
      },
      // Delete Feed
      delFeed() {
        this.feeds.splice(this.tempIndex, 1);
        this.tempIndex = 0;
        this.validate = false;
      },
      // When delete feed checking process
      checkForm(index) {
        this.validate = true;
        this.tempIndex = index;
      },
    },
  }
</script>

<style scoped>

</style>