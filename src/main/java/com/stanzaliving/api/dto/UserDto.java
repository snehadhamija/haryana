package com.stanzaliving.api.dto;

import java.util.List;

public class UserDto {

	private int userId;

	private String userName;

	private String password;

	private String status;

	private String mobileNo;

	private String hostel;

	private int hostelID;

	private List<String> userProfiles;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public int getHostelID() {
		return hostelID;
	}

	public void setHostelID(int hostelID) {
		this.hostelID = hostelID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<String> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", password=" + password + ", status=" + status
				+ ", mobileNo=" + mobileNo + ", hostel=" + hostel + ", hostelID=" + hostelID + ", userProfiles="
				+ userProfiles + "]";
	}

	public UserDto(int userId, String userName, String password, String status, String mobileNo, String hostel,
			int hostelID, List<String> userProfiles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.mobileNo = mobileNo;
		this.hostel = hostel;
		this.hostelID = hostelID;
		this.userProfiles = userProfiles;
	}

	public UserDto() {
		super();
	}

}
