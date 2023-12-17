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
          class=""
          clearable
          label="Id"
          ></v-text-field>
          <v-btn class="mb-5" color="primary" @click.prevent="idCheck()">중복 체크</v-btn>

        <v-text-field
          v-model="pw"
          :readonly="loading"
          :rules="[required]"
          type="password"
          clearable
          label="Password"
          placeholder="Enter your pw"
        ></v-text-field>

        <v-text-field
          v-model="email"
          :readonly="loading"
          :rules="[required]"
          clearable
          label="email"
          placeholder="Enter your email"
        ></v-text-field>

        <v-text-field
          v-model="phone"
          :readonly="loading"
          :rules="[required]"
          clearable
          label="phone"
          placeholder="Enter your phone"
        ></v-text-field>

        <br>

        <v-row>
          <v-col>
            <v-btn
              :loading="loading"
              block
              color="primary"
              size="large"
              type="submit"
              variant="elevated"
              @click.prevent="signUpBtn()"
            >
              회원가입
            </v-btn>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12">
            <v-btn
              :loading="loading"
              block
              color="success"
              size="large"
              type="submit"
              variant="elevated"
              @click.prevent="$router.back()"
            >
            뒤로가기
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

const { postSignUp, getUserId, userIdCheck } = useAuth();

const form = ref(false);
const id = ref();
const pw = ref();
const email = ref();
const phone = ref();
const loading = ref(false)

const onSubmit = () => {
  if(!form.value) return
  loading.value = true
  setTimeout(() => {
    loading.value = false
  }, 2000);
}

const required = (v) => {
  return !!v || 'Field is required'
}

const postSignUpMethod = () => {
  postSignUp(id.value, pw.value, email.value, phone.value);
  id.value = ''
  pw.value = ''
  email.value = ''
  phone.value = ''
}

const signUpBtn = () => {
  if(!id.value) {
    alert("아이디를 입력해주세요")
    return 
  } else if(!pw.value) {
    alert("비밀번호를 입력해주세요")
    return 
  } else if(!email.value) {
    alert("이메일을 입력해주세요")
    return 
  } else if(!phone.value) {
    alert("전화번호를 입력해주세요")
    return
  }
  
  if(userIdCheck.value === 0) {
    postSignUp(id.value, pw.value, email.value, phone.value);
    id.value = ''
    pw.value = ''
    email.value = ''
    phone.value = ''
  } else {
    alert("아이디 중복체크는 필수입니다.")
  }
}

const idCheck = async () => {
  await getUserId(id.value);
  console.log(userIdCheck.value)
  if(userIdCheck.value === 1) alert("중복된 아이디 입니다.")
  else alert('사용 가능한 아이디 입니다.')
}

</script>

<style lang="scss" scoped>

</style>