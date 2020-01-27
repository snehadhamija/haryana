package com.stanzaliving.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
	private String email;

}
