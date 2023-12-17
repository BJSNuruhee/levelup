import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: () => import('@/views/HelloWorld.vue')
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: () => import('@/views/auth/LoginView.vue')
  },
  {
    path: '/signUp',
    name: 'SignUpPage',
    component: () => import('@/views/auth/SignUpView.vue')
  },
];

const router = createRouter({
  history: createWebHistory('/'),
  routes
});

export default router;