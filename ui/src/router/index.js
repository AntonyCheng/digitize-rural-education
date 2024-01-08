import { createRouter, createWebHistory } from 'vue-router'
import loginView from '@/views/loginView.vue'
import first from '@/views/first.vue'
import register from '@/views/register.vue'
import adminMain from '@/views/admin/main.vue'
import userMain from '@/views/user/main.vue'
import ManageUsers from '@/views/admin/ManageUsers.vue'
import ResourcesSharing from '@/views/admin/ResourceSharing.vue'
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
    component:adminMain,
    children:[
      {
        path:'/adminMain/users',
        name:'ManageUsers',
        component:ManageUsers
      },
      {
        path:'/adminMain/sharing',
        name:'ResourceSharing',
        component:ResourcesSharing
      }
    ]
  },
  {
    path:'/register',
    name:'register',
    component:register
  },
  {
    path:'/userMain',
    name:'userMain',
    component:userMain,
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
