<template>
  <v-app>
    <v-navigation-drawer app v-if="drawer">
      <v-list>
        <v-subheader>REPORTS</v-subheader>
        <v-list-item-group v-model="selectedIndex" color="primary">
          <v-list-item v-for="(menu, index) in menus" :key="index" @click="navigateTo(menu.path)">
            <v-list-item-icon>
              <v-icon v-text="menu.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="menu.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>      
    </v-navigation-drawer>
    <v-app-bar color="deep-purple accent-4" dark app>
      <v-app-bar-nav-icon @click.prevent="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title style="float: left">
        Vuetify - Vue
      </v-toolbar-title>

      <div class="flex-grow-1"></div>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-menu
        left
        bottom
      >
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Option {{ n }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>  

    </v-app-bar>

    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    drawer:false,
    selectedIndex: 0,
    menus: [
      { text: 'Home', icon: 'mdi-home', path: '/'},
      { text: 'Contacts', icon: 'mdi-contacts', path: '/contacts'},
      { text: 'Feeds', icon: 'mdi-forum', path: '/feeds'},
      { text: 'To-Do', icon: 'mdi-format-list-checks', path: '/todo'},
      { text: 'Test', icon: 'mdi-format-list-checks', path: '/test'},
    ],
  }),
  methods: {
    navigateTo(path) {
      if(this.$route.path != path) {
        this.$router.push(path);
      }
    },
  },
  watch: {
    $route (to) {
      this.menus.forEach((item, index) => {
        if(to.path == item.path) {
          this.selectedIndex = index;
        }
      });
    }
  }
};
</script>
