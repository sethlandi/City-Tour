package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JdbcUserInfo implements UserInfoDao{

	private JdbcTemplate jdbcTemplate;
	
	public JdbcUserInfo(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(int userID, UserInfo userInfo) {
		
		String sqlSearchForUser = "INSERT INTO userInfo(userid, username, email, firstname, lastname, phoneNumber)"
								+ "VALUES (?, ?, ?, ?,? ,?)"; 
		
		jdbcTemplate.update(sqlSearchForUser,userID, userInfo.getUsername(), userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPhoneNumber());
      
	}

	@Override
	public List<UserInfo> getInfoByUserID(int userID) {
		
		List<UserInfo> userInfo = new ArrayList<UserInfo>();
		
		String sqlInfoById = "SELECT username, email, firstname, lastname, phonenumber " 
						   + "FROM  userinfo " 
						   + "WHERE userid = ?";
		
		SqlRowSet results= jdbcTemplate.queryForRowSet(sqlInfoById, userID);
		while (results.next()) {
			UserInfo theUserInfo = mapRowToUserInfo(results);
			userInfo.add(theUserInfo);
		}
		return userInfo;
	}
	
	@Override
	public void update(int userID, UserInfo userInfo) { //DO WE WANT THEM TO UPDATE USERNAME TOO?
		String sqlDeleteItineraryDetails = "UPDATE userinfo" 
										 + "SET email = ?, firstname = ?, lastname = ?, phonenumber = ?"
				   						 + "WHERE userid = ?";
		
		jdbcTemplate.update(sqlDeleteItineraryDetails,userInfo.getEmail(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPhoneNumber(), userID);
		
	}
	
	private UserInfo mapRowToUserInfo(SqlRowSet results) {
		
		UserInfo theUserInfo = new UserInfo();
		
		theUserInfo.setUserID(results.getInt("userid"));
		theUserInfo.setUsername(results.getString("username"));
		theUserInfo.setEmail(results.getString("email"));
		theUserInfo.setFirstName(results.getString("firstname"));
		theUserInfo.setLastName(results.getString("lastname"));
		theUserInfo.setPhoneNumber(results.getInt("phonenumber"));
		
		return theUserInfo;
}

}
