<template>
  <div id="theDetails">
    <p class="place" >{{ placeInfo.result.name }}</p>
    <div class="info">
      <p class="address">{{ placeInfo.result.formatted_address }}</p>
      <p class="phone">{{ placeInfo.result.formatted_phone_number }}</p>
      <!-- <a class="website" href="placeInfo.result.website" taget="_blank">
        <button>Click here for website</button></a> -->
      
      <button
        @click="linkTab(placeInfo.result.website)"
        class="website">
        Website
      </button>

      <!-- where we add the itinerary to the list -->
      <a href="#" v-on:click.prevent="showForm = true" v-if="showForm === false"><button class='addedItintext'>Add to Itinerary</button></a>
      <form v-if="showForm === true" v-on:submit.prevent="addNewItinerary">
        <div class="form-element">
          <label for="aItinerary">Choose a Itinerary</label>
          <select id="aItinerary" type="text" v-model="itineraryID" >
              <option v-for='itin in itineraryList' :key="itin.id" :value=itin.itineraryID>{{itin.itineraryName}}</option>
          </select>
          <input type = 'text' placeholder="YYYY-DD-MM" v-model = 'date' />
        </div>
        <button id = 'addedItintext' v-on:click.prevent="addNewItineraryItem()" type="submit">
          Add to Itinerary
        </button>
        <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
      </form>

      <div class="open">
        <p
          v-for="day in placeInfo.result.opening_hours.weekday_text"
          :key="day.id"
        >
          {{ day }}
        </p>
      </div>
    </div>
    <img class="placeImg" :src="photoID" alt="" />

  <div id='reviewHero'>
    <h1>Here are the 5 latest reviews</h1>
    <p class="rating">Overall User Rating: {{ placeInfo.result.rating }}</p>
  </div>

    
    <div id="reviews">
      <div
        v-for="review in placeInfo.result.reviews"
        :key="review.id"
        class="aReview">
        <img :src="review.profile_photo_url" />
        <div class="revInfo">
          <h1>{{ review.author_name }}</h1>
          <h5>User Rating: {{ review.rating }}</h5>
        </div>
        <p>{{ review.text }}</p>
      </div>
    </div>


  </div>

  <!-- https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJYYPnG7wsDogR8KApuOJNQw4&fields=name,rating,formatted_phone_number,reviews,website,formatted_address,opening_hours,price_level&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8 -->
</template>

<script>
export default {
  components: {},
  props: {
    placeID: String,
    photoID: String
  },
  data() {
    return {
//       "pickedLocation": "HOB",
// "dateSelected": "2020-01-02",
// "apiPlaceID": "1234567",
// "liked": 0
      itineraryPlace:{
        pickedLocation: '',
        dateSelected:'',
        apiPlaceID:'',
        liked:1
      },
      username:'testy',
      linkSite: "",
      placeInfo: [],
      proxyurl: "https://cors-anywhere.herokuapp.com/",
      url: "https://maps.googleapis.com/maps/api/place/details/json?place_id=",
      urlEnd:
        "&fields=name,rating,formatted_phone_number,reviews,website,formatted_address,opening_hours,price_level&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8",
      photoUrl:
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyAso42k5sym5xcujpYvGz9RgPcmpo8HUH8&photoreference=",
      showForm: false,
      date: "",
      itinerary: [],
      itineraryID:'',
      itineraryList:[],
      addPlaceURL: 'http://localhost:8080/Final-Capstone-Backend-Server/api/Details/'
    };
  },
  methods: {
    linkTab(webSite) {
      window.open(webSite, "_blank");
    },
    addNewItineraryItem() {
      console.log("You've made it to the button!");
      this.itineraryPlace.pickedLocation = this.placeInfo.result.name 
      this.itineraryPlace.apiPlaceID = this.placeID
      this.itineraryPlace.dateSelected = this.date
      fetch(this.addPlaceURL + this.itineraryID,{
        method:'POST',
        headers:{
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.itineraryPlace)
      })
         this.newName = {},
            this.showForm = false
          
            document.getElementsByClassName('addedItintext').innerText ='Added to itinerary'
            
        
      
    },
    resetForm() {
      this.showForm = false; // set the variable that controls the form display to false (hide the form)...
      this.newItineraryItem = {}; // and clear newReview
    },
    saveCat() {
      console.log("place changed");
      localStorage.setItem(
        "newItineraryItem",
        JSON.stringify(this.newItineraryItem)
      );
    }
  },
  created() {
    fetch(this.proxyurl + this.url + this.placeID + this.urlEnd)
      .then(res => {
        return res.json();
      })
      .then(data => {
        this.placeInfo = data;
        console.log(this.placeInfo);
      });
    fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/allItineraries/' + 8)
    .then(res=>{
      return res.json() 
    })
    .then(data =>{
      this.itineraryList = data
    })
  },

  watch: {
    itinerary: {
      handler() {
        console.log("You've made it to the HANDLER!");
        localStorage.setItem("itinerary", JSON.stringify(this.itinerary));
      },
      deep: true
    }
  }
};
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
body {
  background-color: var(--main-bg-color);
}

#theDetails {
  color: white;
  background-color: var(--green);
  display: grid;
  height: 100vh;
  grid-template-columns: 1fr 5fr 5fr 5fr 1fr;
  grid-template-areas:
    ". name name info ."
    ". img img info."
    ". img img open ."
    "hero hero hero hero hero "
    ". rev  rev rev .";
}
#reviewHero{
  grid-area:hero;
  background-color: var(--main-txt-color);
  color: var(--main-bg-color);
  margin: 10px 0;
  text-align: center;
}
.placeImg {
  grid-area: img;
  object-fit: cover;
  width: 100%;
  height: 100%;
  max-height: 250px;
}
.info {
  grid-area: info;
  padding: 50px 15px;
}

.place {
  grid-area: name;
  font-size: 75px;
  margin: 0;
  padding: 5% 0;
}
.address {
  grid-area: address;
}
.phone {
  grid-area: phone;
}
.website {
  grid-area: web;
}
.open {
  grid-area: open;
}
.rating {
  grid-area: rat;
}
#reviews {
  grid-area: rev;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.aReview {
  width: 500px;
  padding: 5px 15px;
  margin: 5px;
  background-color: var(--lightBlue);
  color: var(--main-bg-color);
  border-radius: 0%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "Rimg text text text"
    "theInfo text text text";
}

#reviews .revInfo h1 {
  font-size: 2.25vh;
}
#reviews p {
  text-align: justify;
  grid-area: text;
}
#reviews img {
  width: 60%;
  grid-area: Rimg;
  padding: 5px;
  margin: 0 auto;
}
#reviews h5 {
}
#reviews .aReview .revInfo {
  grid-area: theInfo;
  padding: 0px;
  text-align: right;
  margin-right: 25px;
}

.aReview:hover {
  /* bottom:10px;
  box-shadow: 10px 10px var(--tan); */
}
</style>
