import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
  // 다른 설정들 추가 가능
});

export default axiosInstance;