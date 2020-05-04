package com.techelevator.model;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcItineraryDao implements ItineraryDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcItineraryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Itinerary> getItinerariesByUserID(int userID) {
		List<Itinerary> itineraries = new ArrayList<Itinerary>();
		System.out.println("userid " + userID);
		String sqlgetItineraryById = "SELECT itinerary.itineraryid, itinerary.itineraryname "  
								   + "FROM itinerary "  
								   + "INNER JOIN useritinerary ON useritinerary.itineraryid = itinerary.itineraryid " 
								   + "INNER JOIN userinfo ON userinfo.userid = useritinerary.userid " 
								   + "WHERE useritinerary.userid = ?";	

		jdbcTemplate.queryForRowSet(sqlgetItineraryById, userID);
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlgetItineraryById, userID);
		
		while (results.next()) {
		Itinerary anItinerary = mapRowToItinerary(results);
		itineraries.add(anItinerary);
		}
				
		return itineraries;
	}

	public void save(Itinerary itinerary, int userID) {
		String sqlInsertItinerary = "INSERT INTO itinerary(itineraryid, itineraryname) "
							   	  + "VALUES(?, ?)";
		
		String sqlInsertUserItin = "INSERT INTO useritinerary (itineraryid, userid) "  
								 + "VALUES ( ?, ?)";										
		
		int id = getNextId();	
		jdbcTemplate.update(sqlInsertItinerary, id, itinerary.getItineraryName());
		jdbcTemplate.update(sqlInsertUserItin, id, userID);
		itinerary.setItineraryID(id);
	}
	
	public void delete(int userID, Itinerary itinerary) {
		String sqlDeleteUserItinerary = "DELETE "
									  + "FROM useritinerary "
									  + "WHERE itineraryid = ? AND userid = ?";
		
		String sqlDeleteItineraryDetails = "DELETE "
										 + "FROM itinerarydetails "
										 + "WHERE itineraryid = ?";
		
		String sqlDeleteItinerary = "DELETE "
				  				  + "FROM itinerary "
				  				  + "WHERE itineraryid = ?";

		jdbcTemplate.update(sqlDeleteUserItinerary, itinerary.getItineraryID(), userID);
		jdbcTemplate.update(sqlDeleteItineraryDetails, itinerary.getItineraryID());
		jdbcTemplate.update(sqlDeleteItinerary, itinerary.getItineraryID());
	}

	@Override
	public void update(int userID, Itinerary itinerary) {
		String sqlUpdateItineraryDetails = "UPDATE itinerary " 
				 						 + "SET itineraryname = ? "
				 						 + "WHERE itineraryid = ?";

		jdbcTemplate.update(sqlUpdateItineraryDetails,itinerary.getItineraryName(), userID);
		
	}
		
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('itinerary_itineraryid_seq')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		if (result.next()) {
			return result.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting the next itinerary id");
		}
	}
	
	private Itinerary mapRowToItinerary(SqlRowSet results) {

		Itinerary anItinerary = new Itinerary();
		anItinerary.setItineraryID(results.getInt("itineraryid"));
		anItinerary.setItineraryName(results.getString("itineraryname"));

		return anItinerary;
	}
}


