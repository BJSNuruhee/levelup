import { createRouter, createWebHistory} from 'vue-router';
import HomeView from '@/views/HomeView.vue';

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  }
];

const router = createRouter({
  history: createWebHistory('/'), // 루트 경로를 설정할 수 있다.
  routes
});

export default router;

/* 

두가지는 #이 붙고 안붙고의 차이가 있다.

운영서버에서 배포할 때 차이가 있다.
 - 운영서버에서 배포할 때는 index.html 페이지 1개로 배포한다.

대부분의 서버에서는 / 경로로 요청하면 index.html 을 돌려준다.

-----------------------------------------------------------

createWebHistory
- 전체 경로를 포함해서 자원을 요청한다.

- 전체 경로가 포함되기 때문에 build 할 때 생성된 index.html을 브라우저에서 읽을 수 없다.

- 즉, WebHistory 모드로 운영서버에 배포하게 되면 URL로 진입했을 때, 페이지를 찾을 수 없다.(404)
  - 때문에 WebHistory 모드로 배포 시 추가 설정이 필요하다.
  - https://router.vuejs.kr/guide/essentials/history-mode.html

-----------------------------------------------------------

createWebHashHistory
- hash mode로 자원 요청 시 해쉬 뒤에 값들은 보내지 않는다.
  - # 뒤에 문자열 제거 후 루트로만 요청

- 어차피 서버에 요청을 / 로만 보내기 때문에 배포 후 바로 운영 가능하다.

- 그러나 포털사이트에서 검색 시 노출이 되지 않는다.
  - SEO (검색엔진최적화) 에 나쁜 영향을 미친다.
  - 검색엔진은 #을 페이지로 생각안하고 무시하기 때문
  - 이러한 사유 때문에 History mode로 운영 서버에 배포한다.

*/