package com.techelevator.controller;


import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Itinerary;
import com.techelevator.model.ItineraryDao;

@RestController 
@CrossOrigin 
@RequestMapping("/api/allItineraries/{userID}")
public class ItinerariesController {
	
	@Autowired
	ItineraryDao theItineraries;
	
	public ItinerariesController(ItineraryDao itineraryDao) {
		this.theItineraries = itineraryDao;
	}
	
	@GetMapping 
	public List<Itinerary> getItinerariesByUserID(@PathVariable int userID) {  
		
		List<Itinerary> anItinerary =  theItineraries.getItinerariesByUserID(userID);
		
		if (anItinerary != null) {  
			return anItinerary;
		}
		else {  
			throw new NullPointerException("Captain's Log, supplemental. We have lost visual with "
										 + "the data and our attempts to restore communication with this have been "
										 + "unsuccessful. One thing that is certain however, we are something went wrong.");
		}
	
	}
	
	@PostMapping                       
	@ResponseStatus(HttpStatus.CREATED)
	public void addItinerary(@RequestBody Itinerary itinerary, @PathVariable int userID) { 
		theItineraries.save(itinerary, userID);                                 
	}
	
	
	@DeleteMapping   
	public void delete(@PathVariable int userID ,@RequestBody Itinerary itinerary) {
		theItineraries.delete(userID, itinerary);
	}
	
	@PutMapping
	public void update(@PathVariable int userID, @RequestBody Itinerary itinerary) {
		theItineraries.update(userID, itinerary);
	}
}

