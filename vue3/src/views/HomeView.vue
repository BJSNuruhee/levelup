<template>
  <div>
    <button @click="axiosGet">백엔드로 get요청</button>
    <button @click="axiosPost">백엔드로 post요청</button>
  </div>
  <div>
    <h4>get data 확인 : {{ getData }}</h4>
    <h4>post 전송 성공했나? : {{ postData }}</h4>
  </div>
  <h1></h1>
</template>

<script setup>
// import axios from 'axios';
import axiosInstance from '@/api/index.js';
import { ref } from 'vue';

const getData = ref();
const postData = ref();
const postRequest = {
  name: "비제이시스템즈",
}

const axiosGet = () => {
  axiosInstance.get('/get/test/data')
  .then(res => {
    getData.value = res.data
  }).catch(err => {
    console.log(err)  
  }); 
};

const axiosPost = () => {
  axiosInstance.post('/post/test/data', postRequest)
  .then(res => {
    postData.value = res.data
  }).catch(err => {
    postData.value = err
  })
};
</script>

<style lang="scss" scoped></style>