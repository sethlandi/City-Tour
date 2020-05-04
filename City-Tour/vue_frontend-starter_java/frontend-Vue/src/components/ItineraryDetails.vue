<template>
    <div id="itineraryDetails">
        <!-- <h1>{{places}}</h1> -->
        <div class="aStop"  v-for='place in places' :key='place.id'>

            <h1 class="thePlace">{{place.pickedLocation}} </h1>
            
            <div class="icon">
                <router-link
                        id="button"
                        v-bind:to="{
                            name: 'description',
                            params: {
                            placeID: place.apiPlaceID,
                            
                            }
                        }"
                        tag="button"
                        >Click Here For Details!</router-link>
                <img class='trash' :src="require('../assests/trashcan.png')" alt="" @click='deleteClick(place.itineraryDetailsID, place)'>
            </div>
        </div>

    </div>
</template>
<script>
export default {
   name:'ItineraryDetails',
   props:{
       itinID: Number

   },
   data(){
       return{
           deleted:[],
           places:[],
          deleteDetail:{
              itineraryID:''
          }
    
       }
   },
   methods:{
        deleteClick(id,item){
            console.log('you made it here')
            this.deleteDetail.itineraryID = id
            console.log(id + ' '+ item +' '+ this.deleteDetail.itineraryID)

            const index = this.places.map(aPlace => aPlace.itineraryDetailsID).indexOf(id)
            this.places.splice(index,1)
            
            fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/Details/' + this.deleteDetail.itineraryID,{
                method:'DELETE',
                 headers:{
                'Content-Type': "application/json"
            },
            
            body:JSON.stringify(this.deleteDetail)
        
            })
            
            console.log(id )
                    
        }, 




   },
    created(){
 
        fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/Details/'+ this.itinID)
        .then(res =>{
            return res.json()
        })
        .then(data=>{
            this.places = data
            
        })
        localStorage.removeItem("itinID");
    },

}
</script>
<style scoped>
:root {
  font-family: 'Roboto Condensed', sans-serif;
  --main-bg-color: #273331;
  --main-txt-color: #e9e4d9;
  --tan: #c48c5f;
  --lightBlue: #9ec8cf;
  --green: #2a6e7c;
}
.deletedClass{
    background-color: black;
}
#itineraryDetails{
    width: 600px;
    display: flex;
    flex-wrap: wrap;
    margin: 0 auto;
    padding:15px;
    background-color: var(--green);


}
 .aStop{
    background-color: azure;
    margin: 50px 0 0 0;
    padding: 10px;
    box-shadow: 5px 5px 5px black;
    font-size: 1vh;
    width:599px;
    height:50px;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
    "name name icon"
    ;

}
.icon{
grid-area: icon;

}
.thePlace{
    grid-area: name;
}


img{
    max-width: 25px;
}

</style>