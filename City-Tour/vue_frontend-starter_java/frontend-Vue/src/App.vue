<template>
  <div id="app">
    <div id="nav">
      <router-link :to='{name: "home"}' tag='li' exact class="item">Home</router-link>
      <router-link :to='{name: "MyItinerary"}' tag='li' class='item'>Create/View Itineraries</router-link>
      <router-link :to='{name: "searchpage"}' tag='li' class="item">Search Destinations</router-link>
      <!-- <router-link :to='{name: "viewItinerary"}' tag='li' class="item">View Itinerary</router-link> -->

      
    </div>
    <router-view/>
  </div>
</template>
<script>


</script> 
<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@300;400;700&display=swap');
:root {
  font-family: 'Roboto Condensed', sans-serif;
  --main-bg-color: #273331;
  --main-txt-color: #e9e4d9;
  --tan: #c48c5f;
  --lightBlue: #9ec8cf;
  --green: #2a6e7c;
}
#nav {
  background-color: var(--lightBlue);
  box-shadow: 0px 5px 8px black; 
  height: 30px;
  position: fixed; 
  top: 0; 
  width: 100%; 
  transition: top 0.3s;
  display: flex;
  list-style: none;
  justify-content: center;
  margin: 0 auto;

  

}
.item {
font-size: 20px;
margin-left: 20px;
margin-right: 20px;

}
</style>