package com.techelevator.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserInfo {

	 	@NotBlank(message = "Username is required")
	    private String username;
	 	
	 	@Email (message ="That is not a valid email!")
	    @NotBlank(message = "Email is required")
	    private String email;
	    
	    @NotBlank(message = "First Name is required")
	    private String firstName;

	    @NotBlank(message = "Last Name is required")
	    private String lastName;

	    @NotBlank(message = "Phone Number is required")
	    private int phoneNumber;
	    
	    private int userID;
	    
		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(int phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

	   
}
