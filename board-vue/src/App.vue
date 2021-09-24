<template>
  <div>
    <v-app>
      <AppBar/>
      <v-content>
        <router-view/>
      </v-content>
      <Footer/>
      <Modal/>
    </v-app>
  </div>
</template>

<script>
import AppBar from "@/components/AppBar";
import Footer from "@/components/Footer";
import Modal from "@/components/Modal";

export default {
  name: 'App',

  components: {
    Footer,
    AppBar,
    Modal
  },
};
</script>
