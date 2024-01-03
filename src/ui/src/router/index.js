import { createRouter, createWebHistory } from 'vue-router'
import loginView from '@/views/loginView.vue'
import first from '@/views/first.vue'
import register from '@/views/register.vue'
import main from '@/views/admin/main.vue'
const routes = [
  {
    path: '/login',
    name: 'loginView',
    component: loginView
  },
  {
    path:'/',
    name:'first',
    component:first
  },
  {
    path:'/adminMain',
    name:'adminMain',
    component:main,
  },
  {
    path:'/register',
    name:'register',
    component:register
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
