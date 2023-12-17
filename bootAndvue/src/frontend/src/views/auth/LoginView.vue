<template>
  <div v-if="!session">
    <LoginForm></LoginForm>
  </div>
  <div v-else>
    사용자 정보 페이지
  </div>
</template>

<script setup>
import LoginForm from '@/components/auth/LoginForm.vue'
import useAuth from '@/composables/useAuth';

const { session } = useAuth();

</script>

<style lang="scss" scoped>

</style>