import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('./views/About.vue')
    },
    {
      path: '/contacts',
      name: 'contacts',
      component: () => import('./views/Contacts.vue')
    },  
    {
      path: '/feeds',
      name: 'feeds',
      component: () => import('./views/Feeds.vue')
    },
    {
      path: '/todo',
      name: 'todo',
      component: () => import('./views/Todo.vue')
    }, 
  ]
})
