<template>
  <div>
    <v-app>
      <AppBar/>
      <v-main>
        <router-view/>
      </v-main>
      <Footer/>
    </v-app>
  </div>
</template>

<script>
import AppBar from "@/components/AppBar";
import Footer from "@/components/Footer";

export default {
  name: 'App',

  components: {
    Footer,
    AppBar
  },
};
</script>
