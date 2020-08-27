import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueTimeago from 'vue-timeago'

Vue.config.productionTip = false

Vue.use(VueTimeago, {
  name: 'Timeago',
  locale: 'en',
})

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app')
