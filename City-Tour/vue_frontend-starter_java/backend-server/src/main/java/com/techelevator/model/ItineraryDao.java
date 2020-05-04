package com.techelevator.model;

import java.util.List;

public interface ItineraryDao {
	
	public List<Itinerary> getItinerariesByUserID(int userID);

	public void save(Itinerary itinerary, int userID);

	public void delete(int userID, Itinerary itinerary);

	public void update(int userID, Itinerary itinerary);
}
