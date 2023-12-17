import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './router'

loadFonts()

const myApp = createApp(App)

myApp.use(vuetify)
myApp.use(router)
myApp.mount('#app')
