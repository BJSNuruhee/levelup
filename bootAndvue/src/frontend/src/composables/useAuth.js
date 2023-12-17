import axios from 'axios';
import { ref } from 'vue';

// header에서 세션값을 통해 로그인/로그아웃 메뉴 동적 변경
const session = ref();

export default function useAuth() {
const error = ref();
const baseUrl = '/api'
const userIdCheck = ref();

  // 세션 로그인
  // const login = async (id, pw) => {
  //   await axios.post(`${baseUrl}/post/user/login`, {
  //     userId: id,
  //     userPw: pw
  //   })
  //   .then(res => {
  //     if(res.data === 'bad') {
  //       session.value = null
  //     } else {
  //       session.value = res.data
  //     }
  //   }).catch(err => {
  //     error.value = err
  //     console.log(error.value)
  //   })
  // }
  
  // 쿠키 로그인
  const login = async (id, pw) => {
    await axios.post(`${baseUrl}/post/user/cookieLogin`, {
      userId: id,
      userPw: pw
    })
    .then(res => {
      if(res.data === 'bad') {
        session.value = null
      } else {
        session.value = res.data
      }
    }).catch(err => {
      error.value = err
      console.log(error.value)
    })
  }

  // 세션 로그아웃
  // const logout = async () => {
  //   await axios.post(`${baseUrl}/post/user/logout`)
  //   .then(res => {
  //     if(session.value !== null) {
  //       session.value = null
  //     }
  //   }).catch(err => {
  //     error.value = err
  //     console.log(error.value)
  //   })
  // }

  // 쿠키 로그아웃
  const logout = async () => {
    await axios.post(`${baseUrl}/post/user/cookieLogout`)
    .then(res => {
      if(session.value !== null) {
        session.value = null
      }
    }).catch(err => {
      error.value = err
      console.log(error.value)
    })
  }

  // 회원 가입
  const postSignUp = (id, pw, email, phone) => {
    axios.post(`${baseUrl}/post/user/signUp`, {
      userId: id,
      userPw: pw,
      userEmail: email,
      userPhone: phone,
      userToken: `testToken${Math.random}`
    }).then(res => {
      console.log("전송 성공")
    }).catch(err => {
      error.value = err
      console.log(error.value)
    })
    
  }

  // 유저 id 조회
  const getUserId = async (id) => {
    await axios.get(`${baseUrl}/get/user/id`)
    .then(res => {
      const responseData = res.data.some(d => d === id)
      if(responseData) userIdCheck.value = 1
      else userIdCheck.value = 0
      console.log(res.data)
    }).catch(err => {
      error.value = err
      console.log(error.value)
    }) 
  }

  return {
    session,
    error,
    logout,
    login,
    postSignUp,
    getUserId,
    userIdCheck,

  }
}