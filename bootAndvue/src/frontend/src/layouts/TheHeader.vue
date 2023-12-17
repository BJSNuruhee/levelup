<template>
  <div>
    <v-toolbar
      class="bg-indigo-lighten-1"
      dark
      prominent
    >
      <v-toolbar-title @click="$router.push('/')">비제이시스템즈</v-toolbar-title>

      <v-spacer></v-spacer>
      
      <div v-if="session">
        <v-btn icon @click="$router.push('/')">
          <v-icon>mdi-home</v-icon>
        </v-btn>
        <v-btn icon @click="$router.push('/login')">
          <v-icon>mdi-account</v-icon>
        </v-btn>
        <v-btn icon @click="logoutMethod">
          <v-icon>mdi-export</v-icon>
        </v-btn>
      </div>
      <div v-else>
        <v-btn icon @click="$router.push('/')">
          <v-icon>mdi-home</v-icon>
        </v-btn>
        <v-btn icon @click="$router.push('/login')">
          <v-icon>mdi-account</v-icon>
        </v-btn>
      </div>
    </v-toolbar>
  </div>
</template>

<script setup>
import useAuth from '@/composables/useAuth';
import { useRouter } from 'vue-router'

const router = useRouter();
const { session, logout } = useAuth();

const logoutMethod = async() => {
  await logout();
  if(session.value === null) {
    alert("로그아웃 성공")
    router.push('/') 
  }
}
</script>

<style lang="scss" scoped>

</style>