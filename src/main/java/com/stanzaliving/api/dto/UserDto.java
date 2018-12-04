package com.stanzaliving.api.dto;

public class UserDto {

	private String userName;

	private String mobileNo;

	private String hostel;

	private int hostelID;

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

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", mobileNo=" + mobileNo + ", hostel=" + hostel + ", hostelID="
				+ hostelID + "]";
	}

	public UserDto(String userName, String mobileNo, String hostel, int hostelID) {
		super();
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.hostel = hostel;
		this.hostelID = hostelID;
	}

	public UserDto() {
		super();
	}

}
