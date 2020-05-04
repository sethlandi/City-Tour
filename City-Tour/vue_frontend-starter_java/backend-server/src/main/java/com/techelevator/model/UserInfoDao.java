package com.techelevator.model;

import java.util.List;

public interface UserInfoDao {

	public void save(int userID, UserInfo userInfo);
	
	public void update(int userID, UserInfo userInfo);
	
	public List<UserInfo> getInfoByUserID(int userID);
	}

