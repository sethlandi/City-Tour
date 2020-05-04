<template>
<div>
    <h1>Your Itinerary</h1>
    <ul>
        <li v-for='item in itinerary' :key='item.id'>{{item.location}}</li>
    </ul>
    </div>
</template>



<script>
export default {
    name: "ViewItinerary",


    data() {
        return {
            itinerary: []
        }
    },
    methods: {

},
    created() {
 console.log('App mounted!');
    if (localStorage.getItem('itinerary')) this.itinerary = JSON.parse(localStorage.getItem('itinerary'));
     }
    }
</script>





<style >

</style>