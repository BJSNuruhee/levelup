<template>
  <v-alert v-if="show"
    class="app-alert"
    :color="type"
    :icon="`$${type}`"
    :text="message"
  ></v-alert>
</template>

<script setup>
defineProps({
  show: {
    type: Boolean,
    default: false,
  },
  message: {
    type: String,
    required: true,
  },
  type: {
    type: String,
    validator: (value) => ['success', 'error'].includes(value)
  }
});

</script>

<style scoped>
.app-alert {
  position:fixed;
  top: 80px;
  right: 182px;
}

</style>