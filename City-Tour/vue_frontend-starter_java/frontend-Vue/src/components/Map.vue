<template>
  <div class="Map"/>
</template>

<script>
// import gmapsInit from './utils/gmaps';

export default {
  name: 'Map',

  data() {
      return {
          locations: [
                {
                    position: {
                    lat: 48.160910,
                    lng: 16.383330,
                    },
                },
                {
                    position: {
                    lat: 48.174270,
                    lng: 16.329620,
                    },
                }
  
                ], 
          proxyurl: "https://cors-anywhere.herokuapp.com/",
          url: 'https://maps.googleapis.com/maps/api/js?key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8&libraries=places'
      }
  },
  async mounted() {
    try {
      const google = await gmapsInit();
      const geocoder = new google.maps.Geocoder();
      const map = new google.maps.Map(this.$el);

      geocoder.geocode({ address: 'Austria' }, (results, status) => {
        if (status !== 'OK' || !results[0]) {
          throw new Error(status);
        }

        map.setCenter(results[0].geometry.location);
        map.fitBounds(results[0].geometry.viewport);
      });
    } catch (error) {
      console.error(error);
    }
  },
  
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
}

.App {
  height: 400px;
  width: 100%;
}