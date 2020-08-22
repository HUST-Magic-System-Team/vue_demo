<template>
  <div id="app">
    <h2>两个组件跳转</h2>
    <router-link to="/title" tag="button" >标题</router-link>
    <p></p>
    <router-link to="/text"  tag="button" >正文</router-link>
<!--    <button @click="titleClick">标题</button>-->
<!--    <button @click="textClick">正文</button>-->
    <router-view></router-view>

  </div>
</template>

<script>

export default {
  name: 'App',
  // methods:{
  //   titleClick(){
  //     //通过代码修改路径
  //     this.$router.push('/title')
  //     console.log('titleClick');
  //   },
  //   textClick(){
  //     this.$router.push('/text')
  //     console.log('textClick');
  //   }
  // }
}
</script>

<style>
  .router-link-active{
    color: chocolate;
  }

</style>
