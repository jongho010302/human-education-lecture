<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>Delete Feed</v-card-title>
        <v-card-text>Are you sure want to delete it?</v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue darken-1" text @click="yes">Yes</v-btn>
          <v-btn color="blue darken-1" text @click="no">No</v-btn>
        </v-card-actions>        
      </v-card>
    </v-dialog>
  </div>
</template>


<script>
  export default {
    name: 'validate-form',
    data() {
      return {
        dialog: true,
      }
    },
    methods: {
      yes() {
        this.$emit('validateYes');
        this.dialog = false;
      },
      no() {
        this.$emit('validateNo');
        this.dialog = false;
      },      
    }
  } 
</script>