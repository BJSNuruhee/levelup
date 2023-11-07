<template>
  <TheHeader></TheHeader>
  <TheBody></TheBody>
  <TheFooter></TheFooter>
</template>

<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheBody from '@/layouts/TheBody.vue';
import TheFooter from '@/layouts/TheFooter.vue';

</script>

<style lang="scss" scoped>

</style>


<!-- 

해당 구성을 해야되는 이유

1. 웹페이지의 구조화를 통해 재사용성을 극대화 한다.
 - 레이아웃을 컴포넌트로 만들어두면 여러 페이지에서 재사용이 가능하다.
 - 특히 header와 footer의 경우 모든 페이지에서 쓰이기 때문에 독립 모듈로 만들어야 한다.
 - 때문에 layouts 디렉토리에는 구조화될 모듈을 저장한다. 

2. 유지 보수에 좋다.
 - 수정될 컴포넌트를 바꿔도 다른 곳에 영향을 미치지 않는다.

3. SEO 최적화가 가능하다.
 - Header는 사이트의 로고, 메뉴, 검색창 등 중요한 정보를 담고, 
   Footer는 연락처 정보, 사이트 맵, 저작권 정보 등을 담을 수 있다.
   이러한 정보는 검색 엔진이 웹 페이지의 콘텐츠를 이해하고 색인할 때 도움이 된다.

 -->