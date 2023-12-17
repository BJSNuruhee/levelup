import { ref } from 'vue';

// 전역으로 선언하여 상태를 관리

export default function useAlert() {
  const alerts = ref([]);
  const vAlert = (message, type) => {
    alerts.value.push({ message, type});
    setTimeout(() => {
      alerts.value.shift();
    }, 2000);
  };

  const vSuccess = message => vAlert( message, 'success');
  const vError = message => vAlert( message, 'error' );

  return {
    alerts,
    vSuccess,
    vError,

  }
}