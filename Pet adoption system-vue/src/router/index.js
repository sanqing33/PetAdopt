import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            component: () => import('@/components/index.vue'),
        },
        {
            path: '/user',
            children: [
                {
                    path: '/',
                    component: () => import('@/components/UserSet.vue')
                },
                {
                    path: '/user/login',
                    name: 'Login',
                    component: () => import('@/views/login.vue')
                },
                {
                    path: '/user/regist',
                    name: 'Regist',
                    component: () => import('@/views/regist.vue')
                },
                {
                    path: '/admin/login',
                    name: 'AdminLogin',
                    component: () => import('@/views/admin-login.vue')
                },
            ]
        },
        {
            path: '/admin',
            component: () => import('@/views/System admin.vue'),
            children: [
                {
                    path: '/admin',
                    name: 'AdminHome',
                    component: () => import('@/components/admin/home.vue')
                },
                {
                    path: '/admin/pet',
                    name: 'PetInformation',
                    component: () => import('@/components/admin/PetInformation.vue')
                },
                {
                    path: '/admin/user',
                    name: 'UserInformation',
                    component: () => import('@/components/admin/UserInformation.vue')
                },
                {
                    path: '/admin/adopt',
                    name: 'AdminAdopt',
                    component: () => import('@/components/admin/Adopt.vue')
                },
                {
                    path: '/admin/adoption',
                    name: 'AdminAdoption',
                    component: () => import('@/components/admin/Adoption.vue')
                },
            ]
        },
        {
            path: '/guide',
            name: 'PetGuide',
            component: () => import('@/components/PetGuide.vue')
        },
        {
            path: '/adopt',
            name: 'AdoptionApplication',
            component: () => import('@/components/AdoptionApplication.vue')
        },
        {
            path: '/user',
            name: 'UserSet',
            component: () => import('@/components/UserSet.vue')
        },
    ]
})

export default router
