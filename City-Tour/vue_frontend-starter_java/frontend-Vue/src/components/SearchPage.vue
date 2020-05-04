<template>
  <div id='searchPage'>
    <div id="selectBar">
      <form>
        <label for="cars">Choose a Category:</label>

        <select v-model="type">
          <option value="tourist_attraction">Unique to Chicago</option>
          <option value="amusement_parks">Amusement Park</option>
          <option value="aquarium">Aquarium</option>
          <option value="art_gallery">Art Gallery</option>
          <option value="bar">Bar</option>
          <option value="casino">Casino</option>
          <option value="cemetary">Cemetary</option>
          <option value="church">Church</option>
          <option value="city_hall">City Hall</option>
          <option value="courthouse">Courthouse</option>
          <option value="library">Library</option>
          <option value="museum">Museum</option>
          <option value="night_club">Night Club</option>
          <option value="park">Park</option>
          <option value="restaurant">Restaurant</option>
          <option value="zoo">Zoo</option>
        </select>
        <button v-on:click="saveCat()" type="submit">Submit</button>
      </form>
    </div>
    <div id="places">
      <div class="aPlace" v-for="place in places.results" :key="place.id">
        <h1 class="name">{{ place.name }}</h1>

        <img
          class="img"
          :src="photoUrl + place.photos[0].photo_reference"
          alt=""
          v-bind:photoID="photoUrl + place.photos[0].photo_reference"
        />
        <router-link
          id="button"
          v-bind:placeID="places.place_id"
          v-bind:to="{
            name: 'description',
            params: {
              placeID: place.place_id,
              photoID: photoUrl + place.photos[0].photo_reference
            }
          }"
          tag="button"
          >Click Here For Details!</router-link>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "SearchPage",
  photoAPI:
    "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference='{{place.photos[0].photo_reference}}'&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8",
  data() {
    return {
      placeID: "",
      photoID: "",
      places: [],
      proxyurl: "https://cors-anywhere.herokuapp.com/",
      url:
        "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.8781,-87.6298&radius=21000&types=",
      type: localStorage.getItem("type"),
      urlEnd: "&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8",
      photoUrl:
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8&photoreference="
    };
  },
  methods: {
    saveCat() {
      console.log("place changed");
      localStorage.setItem("type", JSON.stringify(this.type));
    },
    showPlaces() {
      this.places.results.forEach(place => {
        console.log(place.name);
        console.log(place.opening_hours);
      });
    }
  },
  created() {
    if (localStorage.getItem("type")) {
      this.type = JSON.parse(localStorage.getItem("type"));
    }
    fetch(this.proxyurl + this.url + this.type + this.urlEnd)
      .then(response => {
        return response.json();
      })
      .then(data => {
        this.places = data;
      })
      .catch(err => {
        console.error(err);
      });
    localStorage.removeItem("type");
  }
};
</script>
<style >
:root {
  --main-bg-color:#273331;
  --main-txt-color: #E9E4D9;
  --tan: #C48C5F;
  --lightBlue: #9EC8CF;
  --green: #2A6E7C;
}
#places {
  display: flex;
  flex-wrap: wrap;
  width: 700px;
  margin: 0 auto;
  background-color: var(--green);
  justify-content: space-evenly;
}

.aPlace {
  color: var(--main-bg-color);
  width: 300px;
  margin: 15px;
  height: 210px;
  max-height: 250px;
  background-color: var(--tan);
  border-radius: 15px;
  
}
#places img{
  width: 100%;
  height: 150px;
  max-height: 150px;
  object-fit: cover;
  margin: 0;
  padding: 0;
}
.name{
  font-size: 1vh;
  text-transform: uppercase;
  padding: 5px 10px;
  margin: 0px;
}
#searchPage #button {
  font-size: large;
  height: 30px;
  border-radius: 10px;
  width: 100%;
  display: flex;
  align-items: flex-end;

}

#selectBar {
  text-align: center;
  width: 100%;
  padding-top: 2.4%;
  background-color: var(--lightBlue);
  
  margin: 0 auto;
 
}

</style>
