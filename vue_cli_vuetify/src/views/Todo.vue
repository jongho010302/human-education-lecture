<template>
  <div>
    <v-container style="background-color: #ECF0F1;">
      <v-row>
        <v-col cols="12" md="6">
          <v-card>
            <v-card-title>To do</v-card-title>
            <v-text-field class="ml-4 mr-4" v-model="text" @keydown.13="add"/>
            <v-card-text class="pt-1">
              <div class="float-right">
                <v-btn class="mr-1" outlined color="blue" @click="add">add</v-btn>
                <v-btn outlined color="green" @click="allDel">Mark all as done</v-btn>
              </div>
              <div style="clear:both;"></div>
              <v-list-item-group> 
                <v-list-item  v-for="(item, index) in items" :key = "index">
                  <v-icon @click="del(item, index)" class="mr-1">mdi-check</v-icon> {{item.text}}
                </v-list-item>
              </v-list-item-group>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card>
            <v-card-title>Already Done</v-card-title>
            <v-card-text >
              <v-list-item v-for="(item, index) in doneItem" :key="index">
                 <v-icon size="19" class="mr-1" @click="done(index)">fas fa-edit</v-icon> {{item.text}}
              </v-list-item>
            </v-card-text>
          </v-card>
        </v-col>         
      </v-row>
    </v-container>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        text: '',
      item: 1,
      items: null,
      doneItem: null,
      }
    },
    created() {
      this.items = [];
      this.doneItem = [];
    },
    methods: {
      add() {
        this.items.unshift( { text: this.text });
        this.text = '';
      },
      del(item, index) {
        this.items.splice(index, 1);
        this.doneItem.push( { text: item.text } );
      },
      allDel() {
        this.doneItem = this.items.concat(this.doneItem);
        this.items = [];
      },
      done(index) {
        this.doneItem.splice(index, 1)
      }
    }
  }
</script>

<style scoped>

</style>