<template>
  <div class="Navbar">
    <b-navbar toggleable="lg" type="dark" style="background-color: darkblue">
      <b-navbar-brand to="/">JIAH-LEA 🐻</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
<b-navbar-nav style="margin-left:600px;">
  <b-nav-item to="/postList">게시판</b-nav-item>
</b-navbar-nav>
        <b-navbar-nav class="ml-auto">
            <b-nav-item to="/login">SignIn</b-nav-item>
            <b-nav-item to="/signup" >SignUp</b-nav-item>
<!--          <b-nav-form>-->
<!--            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>-->
<!--            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>-->
<!--          </b-nav-form>-->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
    export default{

    }
</script>

<style scoped>
.Navbar{
  font-family: this_is_font_name;
}
</style>
