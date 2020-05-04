package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class ItineraryDetails {
	
	private int itineraryID;
	
	private int itineraryDetailsID;
	
	@NotBlank(message = "Location is required")
	private String pickedLocation;
	
	@NotBlank(message = "Date is required")
	private String dateSelected;
	
	@NotBlank(message = "API PlaceID is required")
	private String apiPlaceID;
	
	@NotBlank(message = "Liked is required")
	private int liked;
	
	
	
	public int getItineraryID() {
		return itineraryID;
	}
	public void setItineraryID(int itineraryID) {
		this.itineraryID = itineraryID;
	}
	public int getItineraryDetailsID() {
		return itineraryDetailsID;
	}
	public void setItineraryDetailsID(int itineraryDetailsID) {
		this.itineraryDetailsID = itineraryDetailsID;
	}
	public String getPickedLocation() {
		return pickedLocation;
	}
	public void setPickedLocation(String pickedLocation) {
		this.pickedLocation = pickedLocation;
	}
	public String getDateSelected() {
		return dateSelected;
	}
	public void setDateSelected(String date) {
		this.dateSelected = date;
	}
	public String getApiPlaceID() {
		return apiPlaceID;
	}
	public void setApiPlaceID(String apiPlaceID) {
		this.apiPlaceID = apiPlaceID;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	
}

