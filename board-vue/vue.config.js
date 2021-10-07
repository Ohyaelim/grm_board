module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  outputDir: "../board/src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    proxy: 'http://localhost:8080'
  }
}
