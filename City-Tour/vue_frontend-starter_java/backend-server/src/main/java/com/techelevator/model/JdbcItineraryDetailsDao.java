package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JdbcItineraryDetailsDao implements ItineraryDetailsDao {
	
    private JdbcTemplate jdbcTemplate;
    
	public JdbcItineraryDetailsDao(DataSource datasource) {
	   	jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public void save(int itineraryID , ItineraryDetails theDetails) {
		String sqlInsertItineraryDetails = "INSERT INTO itinerarydetails(itineraryid, pickedlocation, dateselected, apiplaceid, liked ) "
				   						 + "VALUES(?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlInsertItineraryDetails, itineraryID, theDetails.getPickedLocation() ,theDetails.getDateSelected(), theDetails.getApiPlaceID(), theDetails.getLiked());
		theDetails.setItineraryDetailsID(itineraryID);
		
	}
	
	@Override
	public void delete(int itineraryID) {
		String sqlDeleteItineraryDetails = "DELETE " 
										 + "FROM itinerarydetails "
				   						 + "WHERE itinerarydetailsid = ?";
		
		jdbcTemplate.update(sqlDeleteItineraryDetails, itineraryID);
		
	}
	
	@Override
	public void update(int itineraryID, ItineraryDetails theDetails) {
		String sqlDeleteItineraryDetails = "UPDATE itinerarydetails " 
										 + "SET dateselected = ? "
				   						 + "WHERE itineraryid = ?";
		
		jdbcTemplate.update(sqlDeleteItineraryDetails,theDetails.getDateSelected(), itineraryID);
		
	}
	
	@Override
	public List<ItineraryDetails> getItineraryDetailsByItineraryID(int itineraryID) {
		List<ItineraryDetails> itineraryDetails = new ArrayList<ItineraryDetails>();

		String sqlgetItineraryDetailsByItineraryID = "SELECT * "
												   + "FROM itinerarydetails "
												   + "WHERE itineraryid = ?";
												   

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetItineraryDetailsByItineraryID, itineraryID);

		while (results.next()) {
			ItineraryDetails theItineraryDetails = mapRowToItineraryDetails(results);
			itineraryDetails.add(theItineraryDetails);
		}

		return itineraryDetails;
	}

	private ItineraryDetails mapRowToItineraryDetails(SqlRowSet results) {

		ItineraryDetails theItineraryDetails = new ItineraryDetails();
		
		theItineraryDetails.setItineraryID(results.getInt("itineraryid"));
		theItineraryDetails.setItineraryDetailsID(results.getInt("itinerarydetailsid"));
		theItineraryDetails.setPickedLocation(results.getString("pickedlocation"));
		theItineraryDetails.setDateSelected(results.getString("dateselected"));
		theItineraryDetails.setApiPlaceID(results.getString("apiplaceid"));
		theItineraryDetails.setPickedLocation(results.getString("pickedlocation"));
		theItineraryDetails.setLiked(results.getInt("liked"));
		return theItineraryDetails;
	}

}



