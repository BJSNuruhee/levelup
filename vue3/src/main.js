// import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'

// bootstrap
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

console.log(import.meta.env.VITE_APP_API_URL)

createApp(App).use(router).mount('#app')
