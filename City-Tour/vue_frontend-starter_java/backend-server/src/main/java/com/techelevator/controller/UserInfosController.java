package com.techelevator.controller;

import com.techelevator.model.UserInfo;
import com.techelevator.model.UserInfoDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//
//// ApiController 
//@RestController 
//@CrossOrigin 
//@RequestMapping("/api/UserInfo/{userid}")
//public class UserInfosController {
//	
//	@Autowired
//	UserInfoDao theUserInfo;
//	
//	public UserInfosController(UserInfoDao userInfoDao) {
//		this.theUserInfo = userInfoDao;
//	}
//	
//	@GetMapping  
//	public List<UserInfo> returnAllUserInfo(@PathVariable int userID) {
//		
//		List<UserInfo> aDetail =  theUserInfo.getInfoByUserID(userID);
//		
//		if (aDetail != null) {  
//			return aDetail;
//		}
//		else {  
//			throw new NullPointerException("Captain's Log, supplemental. We have lost visual with "
//					 + "the data and our attempts to restore communication with this have been "
//					 + "unsuccessful. One thing that is certain however, we are something went wrong.");
//		} 
//	}
//	
//	@PostMapping                       
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addAReview(@PathVariable int userID, @RequestBody UserInfo userInfo) {                                                                  
//		theUserInfo.save(userID, userInfo);                                   
//	}
//	
//	@PutMapping
//	public void update(@PathVariable int userID, @RequestBody UserInfo userInfo) {
//		theUserInfo.update(userID, userInfo);
//	}	
//}