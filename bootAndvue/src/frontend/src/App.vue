<template>
  <v-app>
    <v-main class="w-75 mx-auto">
      <TheHeader/>
      <TheView/>
      <TheFooter/>
    </v-main>
  </v-app>
</template>

<script>
import TheHeader from './layouts/TheHeader.vue'
import TheView from './layouts/TheView.vue'
import TheFooter from './layouts/TheFooter.vue'

export default {
  name: 'App',

  components: {
    TheHeader,
    TheView,
    TheFooter,
  },

  data: () => ({
    //
  }),
}
</script>
