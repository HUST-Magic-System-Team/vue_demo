<template>
  <div id="mag">
    <h2>{{message}}</h2>
    <h2>计数：{{$store.state.counter}}</h2>
    <button @click="up">+</button>
    <button @click="down">-</button>
    <h2>{{$store.getters.pf}}</h2>

    <hello-vuex></hello-vuex>
  </div>
</template>

<script>
import use_other from "./components/use_other";

export default {
  name: 'App',
  components:[
          use_other
  ],
  comments: {
    use_other
  },
  data() {
    return {
      message: '计数器',
    }
  },
  methods: {
    up() {
      console.log('up被执行');
      this.$store.commit('increment')
    },
    down() {
      console.log('down被执行');
      // 使用 action中的异步方法
      this.$store.dispatch('decrement')
    }
    }
}
</script>

<style>

</style>
