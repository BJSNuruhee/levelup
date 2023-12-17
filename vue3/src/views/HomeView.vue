<template>
  <div>
    <button @click="axiosGet">백엔드로 get요청</button>
    <button @click="axiosPost">백엔드로 post요청</button>
  </div>
  <div>
    <h4>get data 확인 : {{ getData }}</h4>
    <h4>post 전송 성공했나? : {{ postData }}</h4>
  </div>
  <hr>
  <button @click="save(addadd, postRequest)">김앤장</button>
  <button @click="save(bddbdd, reqqq)">김앤장22222222</button>
</template>

<script setup>
// import axios from 'axios';
import axiosInstance from '@/api/index.js';
import { ref } from 'vue';
import { useAdd } from '@/composables/app.js'
import axios from 'axios';

const { addadd, reqqq, bddbdd } = useAdd();
const getData = ref();
const postData = ref();
const postRequest = {
  name: "비제이시스템즈",
}

// const axiosGet = () => {
//   axiosInstance.get('/get/test/data')
//   .then(res => {
//     getData.value = res.data
//   }).catch(err => {
//     console.log(err)  
//   }); 
// };

const axiosGet = () => {
  axios.get('/api/get/test/data')
  .then(res => {
    getData.value = res.data
  }).catch(err => {
    console.log(err)  
  }); 
};

// const axiosPost = () => {
//   axiosInstance.post('/post/test/data', postRequest)
//   .then(res => {
//     postData.value = res.data
//   }).catch(err => {
//     postData.value = err
//   })
// };

const axiosPost = () => {
  axios.post('/api/post/test/data', postRequest)
  .then(res => {
    postData.value = res.data
  }).catch(err => {
    postData.value = err
  })
};

// const add = (request) => {
//   console.log("ad Function a called with request:", request);
// };

// const bdd = (request) => {
//   console.log("bdddddddddd Function a called with request:", request);
// };

const save = async (api, postRequest) => {
  await api(postRequest)
};
</script>

<style lang="scss" scoped></style>