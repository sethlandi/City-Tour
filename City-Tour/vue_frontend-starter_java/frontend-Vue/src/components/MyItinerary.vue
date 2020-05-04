<template>
  <div id="myItinerary">
      <div class="form">
          
            <form v-if="showForm === true" v-on:submit.prevent="addNewItinerary">
            <div class="form-element">
                <label for="newItineraryName">Add your new Itinerary Name</label> <input  type="text" v-model="newName.itineraryName">
            </div>
            <button v-on:click.prevent='addNewItin()' type="submit">Add to Itinerary</button>   
            <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
         </form> 
        </div>

      <div class="aStop" v-for='item in itin' :key='item.id'>
        <h1 class="name">{{item.itineraryName}}</h1>
     <router-link id="button" tag="button" 
        v-bind:itinID =item.itineraryID
        v-bind:to="{
            name: 'itinerarydetails',
            params:{
                itinID: item.itineraryID
            }
            }">View Places</router-link>
          
          <img class='trash' :src="require('../assests/trashcan.png')" alt="" @click='deleteClick(item.itineraryID, item)'>

      </div>
      
  </div>
</template>
<script>

// import Map from './components/Map.vue'
export default {
    name:'MyItinerary',
    components:{
        
    },
    data(){
        return {
            userID:8,
            newName:{
                itineraryName: ''
            },
            showForm: true,
            itin:[],
            username: 'testy',
            itinID: '',
            deleteItin:{
                itineraryID:''
            },
            proxyurl: "https://cors-anywhere.herokuapp.com/",

        }
    },
    methods:{
        addNewItin(){
            fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/allItineraries/'+ this.userID,{
            method:'POST',
            headers:{
                'Content-Type': "application/json"
            },
            
            body:JSON.stringify(this.newName)
            })
            this.itin.push(this.newName)
          
            
        },
        
        deleteClick(id,item){
            this.deleteItin.itineraryID = id
            console.log(item)
             const index = this.itin.map(aPlace => aPlace.itineraryID).indexOf(id)
            this.itin.splice(index,1)
            fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/allItineraries/' + this.userID,{
                method:'DELETE',
                 headers:{
                'Content-Type': "application/json"
            },
            
            body:JSON.stringify(this.deleteItin)
        
            })
            
            console.log(id )
                    
        },   
        saveID(id){
            console.log(id)
            this.itinID = id
            
            localStorage.setItem('itinID', JSON.stringify(this.itinID))
        },
        resetForm(){
            this.newName = {},
            this.showForm = false
        }

    

    },
    created(){
        fetch('http://localhost:8080/Final-Capstone-Backend-Server/api/allItineraries/' + this.userID)
        .then(res =>{
            return res.json()
        })
        .then(data =>{
            this.itin = data
        })
    }

}
</script>
<style scoped >
:root {
  font-family: 'Roboto Condensed', sans-serif;
  --main-bg-color: #273331;
  --main-txt-color: #e9e4d9;
  --tan: #c48c5f;
  --lightBlue: #9ec8cf;
  --green: #2a6e7c;
}

#myItinerary{
    margin: 50px auto;
    width: 600px;
    /* display: flex;
    flex-wrap: wrap; */
    background-color: var(--green);
    padding: 15px;
}
.form{
    background-color: var(--main-txt-color);
    width: 100%;
    display: flex;
    align-content: center;
}
#myItinerary .aStop{
    height: 75px;
    width: 250px;
    margin: 50px auto;
    display: grid;
    padding: 5px;
    grid-template-columns: 5%  1fr 1fr 1fr 1fr 5%;
    grid-template-areas: 
    ". name   name    name    name ."
    ". button button edit     delete ."
    ;
    background-color: var(--tan);
    box-shadow: 5px 5px 5px black;
    
}
#myItinerary .name{
    grid-area:name;
    font-size: 2vh;
    
}
.edit{
    grid-area: edit;
    width: 50%;
  
}
.trash{
        grid-area: delete;
    width: 50%;

    padding:5px;
}
#button{
    grid-area:button;
    height: 100%;
    
}


</style>
