module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  // outputDir: "../src/main/resources/static",
  // indexPath: "../static/index.html",
  devServer: {
    proxy: 'http://localhost:8080'
  }
}
