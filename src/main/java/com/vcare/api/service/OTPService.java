package com.vcare.api.service;

public interface OTPService {

	String generateOTP(int length);

	void sendOTP(String mobileNumber, String otp);

	void sendOTP(String mobileNumber);

}
