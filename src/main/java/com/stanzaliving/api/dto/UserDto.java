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

	private String userCode;

	private String room;

	private String image;

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

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
				+ userProfiles + ", userCode=" + userCode + ", room=" + room + ", image=" + image + "]";
	}

	public UserDto(int userId, String userName, String password, String status, String mobileNo, String hostel,
			int hostelID, List<String> userProfiles, String userCode, String room, String image) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.mobileNo = mobileNo;
		this.hostel = hostel;
		this.hostelID = hostelID;
		this.userProfiles = userProfiles;
		this.userCode = userCode;
		this.room = room;
		this.image = image;
	}

	public UserDto() {
		super();
	}

}
