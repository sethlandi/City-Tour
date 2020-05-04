import Vue from "vue";
import App from "./App";
import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8",
    libraries: "places" //necessary for places input
  }
});

new Vue({
  el: "#app",
  components: { App },
  template: "<App/>"
});