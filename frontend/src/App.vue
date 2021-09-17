<template>
  <div id="app">
    <div id="nav">
      <Navbar/>
<!--      <router-link to="/">Home</router-link> |-->
<!--      <router-link to="/about">About</router-link>-->
    </div>
    <b-container>
    <router-view/>
    </b-container>
  </div>
</template>

<script>
    import Navbar from "@/components/Navbar";
    export default {
      components:{
        Navbar
      }
    }
</script>

<style lang="scss">

</style>
