<template>
<div>
  <!-- Dialog -->  
  <div class="float-right">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <!-- Card Title -->
        <v-card-title><span class="headline">User Profile</span></v-card-title>
        <!-- Card Content -->       
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Name" v-model="dessert.name" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Calroies" v-model="dessert.calories" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Fat" v-model="dessert.fat" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Carbs" v-model="dessert.carbs" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Protein" v-model="dessert.protein" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="Iron" v-model="dessert.iron" required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          <small>indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="add">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-btn class="ma-2" outlined color="deep-purple accent-4" @click="dialog=true">Add</v-btn>
    <v-btn class="my-2" outlined color="normal" @click="del">Delete</v-btn>     
  </div>
  
  <!-- Data Table -->
  <div style="clear: both;">
    <v-data-table v-model="selected" :headers="headers" :items="desserts" item-key="name" show-select class="elevation-1">
    </v-data-table>
  </div>
</div>

</template>

<script>
  export default {
    name: 'contacts',
    data () {
      return {
        dialog: false,
        selected: [],
        dessert: {},
        headers: [
          {
            text: 'Dessert (100g serving)',
            align: 'left',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
        ],
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: '1%',
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: '1%',
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: '7%',
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: '8%',
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: '16%',
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
            iron: '0%',
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
            iron: '2%',
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
            iron: '45%',
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
            iron: '22%',
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
            iron: '6%',
          },
        ],
      }
    },
    methods: {
      add() {
        this.desserts.push(this.dessert);
        this.dialog = false;
        this.dessert = {};
      },
      del() {
        this.selected.forEach(item => {
          this.desserts.splice(this.desserts.indexOf(item), 1);
          console.log(this.selected)
        })
        this.selected = [];
        
      },
    }
  }
</script>

<style scoped>

</style>