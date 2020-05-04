package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Itinerary {
	
    @NotBlank(message = "Itinerary name is required")
    private String itineraryName;
    private int itineraryID;
    private String username;
    
	public String getItineraryName() {
		return itineraryName;
	}
	public void setItineraryName(String itineraryName) {
		this.itineraryName = itineraryName;
	}
	public int getItineraryID() {
		return itineraryID;
	}
	public void setItineraryID(int id) {
		this.itineraryID = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    

}