<template>
  <v-sheet class="pa-12" rounded>
    <v-card class="mx-auto px-6 py-8" max-width="500">
      <v-form
        v-model="form"
        @submit.prevent="onSubmit"
      >
        <v-text-field
          v-model="id"
          :readonly="loading"
          :rules="[required]"
          class="mb-2"
          clearable
          label="Id"
        ></v-text-field>

        <v-text-field
          v-model="pw"
          :readonly="loading"
          :rules="[required]"
          clearable
          label="Password"
          placeholder="Enter your password"
        ></v-text-field>

        <br>

        <v-row>
          <v-col cols="6">
            <v-btn
              :loading="loading"
              block
              color="primary"
              size="large"
              type="submit"
              variant="elevated"
              @click="loginMethod()"
            >
              로그인
            </v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn
              :loading="loading"
              block
              color="success"
              size="large"
              type="submit"
              variant="elevated"
              @click.prevent="$router.push('/signUp')"
            >
            회원가입
            </v-btn>
          </v-col>
        </v-row>

      </v-form>
    </v-card>
  </v-sheet>
</template>

<script setup>
import { ref } from 'vue'
import useAuth from '@/composables/useAuth';
import { useRouter } from 'vue-router'

const { login, session } = useAuth();
const router = useRouter();

const form = ref(false);
const id = ref();
const pw = ref();
const loading = ref(false)

const onSubmit = () => {
  if(!form.value) return
  // loading.value = true
  // setTimeout(() => {
  //   loading.value = false
  // }, 2000);
}

const required = (v) => {
  return !!v || 'Field is required'
}

const loginMethod = async () => {
  await login(id.value, pw.value)
  if(session.value === 'good') {
  alert("로그인 성공")
  router.push('/')
} else {
  alert("로그인 실패")
}
}


</script>

<style lang="scss" scoped>

</style>