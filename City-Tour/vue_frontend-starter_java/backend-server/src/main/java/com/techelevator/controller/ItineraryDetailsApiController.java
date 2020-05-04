package com.techelevator.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.HttpStatus;

import com.techelevator.model.ItineraryDetails;
import com.techelevator.model.ItineraryDetailsDao;


@RestController 
@CrossOrigin 
@RequestMapping("/api/Details/{itineraryID}")
public class ItineraryDetailsApiController {
	
	@Autowired
	ItineraryDetailsDao theItineraryDetails;
	
	public  ItineraryDetailsApiController(ItineraryDetailsDao ItineraryDetailsDao) {
		this.theItineraryDetails = ItineraryDetailsDao;
	}
	
	
	@PostMapping                    
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDetailItem(@PathVariable int itineraryID, @RequestBody ItineraryDetails theDetails) { 
		theItineraryDetails.save(itineraryID, theDetails);
	}
	
	@GetMapping
	public List <ItineraryDetails> getDetailsByID(@PathVariable int itineraryID ) {
		
		List<ItineraryDetails> aDetail =  theItineraryDetails.getItineraryDetailsByItineraryID(itineraryID);
		
		if (aDetail != null) {  
			return aDetail;
		}
		else {
			throw new NullPointerException("Captain's Log, supplemental. We have lost visual with "
					 + "the data and our attempts to restore communication with this have been "
					 + "unsuccessful. One thing that is certain however, we are something went wrong.");
		}
	
	}
	
	@DeleteMapping  
	public void delete(@PathVariable int itineraryID) {
		theItineraryDetails.delete(itineraryID);
	}
	
	@PutMapping
	public void update(@PathVariable int itineraryID, @RequestBody ItineraryDetails theDetails) {
		theItineraryDetails.update(itineraryID, theDetails);
	}
}