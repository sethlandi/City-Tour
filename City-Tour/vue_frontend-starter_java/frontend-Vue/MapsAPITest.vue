<template>

    <div id="map"></div>

</template>

<script>
import gmapsInit from './utils/gmaps';
export default {
    function initMap() {
        let options = {
            zoom: 8,
            center: {lat:41.8781, lng:-87.6298}
        }

        let map = new 
        google.maps.Map(document.getElementById('map'), options);

        let marker = new google.maps.Marker({
            position:{lat:41.8781, lng:-87.6298},
            map:map
        });
    }
};

</script>

<style>
#map {
    height: 400px;
    width: 100%;
}

</style>
