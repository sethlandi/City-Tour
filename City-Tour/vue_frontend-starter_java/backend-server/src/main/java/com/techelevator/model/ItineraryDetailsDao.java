package com.techelevator.model;

import java.util.List;

public interface ItineraryDetailsDao {
	
	public void save(int itineraryID, ItineraryDetails theDetails);
	
	public void delete(int itineraryID);
	
	void update(int itineraryID, ItineraryDetails theDetails);

	List<ItineraryDetails> getItineraryDetailsByItineraryID(int itineraryID);
}