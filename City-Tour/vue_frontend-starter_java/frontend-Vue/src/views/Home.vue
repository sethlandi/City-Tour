<template>
  <div class="home">
    <div class="hero">
    <img src='https://images.unsplash.com/photo-1494522855154-9297ac14b55f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80'/>
    <h1 class="title">Chicago</h1>
    <p class="dez">Welcome to the Windy City Traveler</p>
   <router-link :to='{name: "MyItinerary"}' tag='button' id='button'>Create Itineraries</router-link>

    <!-- <ul>
      <li v-for='place in places.results' :key='place.id'>{{place.name}}</li>
    </ul> -->
    </div>
  </div>
</template>

<script>
export default {
  name:'home',
    props:{
   // places: Array
  },
  components: {},

  data() {
    return{ 
        places: [],
        proxyurl: "https://cors-anywhere.herokuapp.com/",
        url: 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.8781,-87.6298&radius=1500&types=restaurant&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8'
    }
  },
  methods: {
    showPlaces() {
        this.places.results.forEach((place)=>{
        console.log(place.name)
        console.log(place.opening_hours)
        })
    }
  },
  // created() {
  //     fetch(this.proxyurl + this.url)
  //     .then((response)=>{
  //       return response.json();
  //     })
  //     .then((data)=>{
  //       this.places = data
  //             })
  //     .catch((err)=>{
  //       console.error(err)
  //     })
  // }

}




</script>
<style>
:root {
  font-family: 'Roboto Condensed', sans-serif;
  --main-bg-color: #273331;
  --main-txt-color: #e9e4d9;
  --tan: #c48c5f;
  --lightBlue: #9ec8cf;
  --green: #2a6e7c;
}
.hero{
  display:flex;
  flex-direction: column;
  align-items: center;
  position:relative;
  justify-content: center;
  text-align: center;
  min-height:100vh;
  color:white;
}
.hero img{
  position:absolute;
  top:0;
  left:0;
  width: 100%;
  /* height: 100%; */
  background-size: cover;
  z-index: -1;
}
.title {
color:yellow;
text-shadow: 4px 3px black;
font-size: 200px;
padding: 0px;
}
.dez {
  color:yellow;
  text-shadow: 4px 3px black;
  font-size: 40px;
  margin-top: -100px;
}
#button{
  background-color: var(--main-bg-color);
  color: white;
  height: 75px;
  width: 100px;
  border-radius: 25%;
  font-size: 15px;
  border-color: var(--main-bg-color) ;
}

</style>
