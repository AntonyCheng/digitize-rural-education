import { createRouter, createWebHistory } from 'vue-router'
import loginView from '@/views/loginView.vue'
import first from '@/views/first.vue'
import register from '@/views/register.vue'

import userMain from '@/views/user/main.vue'
import sharing_resourceVue from '@/views/user/sharing_resource.vue'
import resource_particularsVue from '@/views/user/resource_particulars.vue'
import fourC_ResourceVue from '@/views/user/fourC_Resource.vue'

import adminMain from '@/views/admin/main.vue'
import ManageUsers from '@/views/admin/ManageUsers.vue'
import ResourcesSharing from '@/views/admin/ResourceSharing.vue'
import CourseCustomization from '@/views/admin/CourseCustomization.vue'
import DigitalAssistant from '@/views/admin/DigitalAssistant.vue'
import FourC_Resource from '@/views/admin/4C_Resource.vue'

import main from '@/views/user/main.vue'
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
        //用户管理
        path:'/adminMain/users',
        name:'ManageUsers',
        component:ManageUsers
      },
      {
        //共享资源
        path:'/adminMain/sharing',
        name:'ResourceSharing',
        component:ResourcesSharing
      },
      {
        //定制资源
        path:'/adminMain/customize',
        name:'CourseCustomize',
        component:CourseCustomization
      },
      {
        path:'/adminMain/fourC',
        name:'4C',
        component:FourC_Resource
      },
      {
        //数字助手
        path:'/adminMain/digital',
        name:'DigitalAssistant',
        component:DigitalAssistant
      }
    ]
  },
  {
    path:'/',
    name:'userMain',
    component:main,
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
    children:[
      {
        path:'/userMain/sharing',
        name:'sharing',
        component:sharing_resourceVue
      },
      {
        path:'/userMain/particulars',
        name:'resource_particulars',
        component:resource_particularsVue
      },
      {
        path:'/userMain/fourC',
        name:'fourC',
        component:fourC_ResourceVue
      }
    ]
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
