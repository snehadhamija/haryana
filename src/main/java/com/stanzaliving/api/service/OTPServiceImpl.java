package com.stanzaliving.api.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.Gateway.SmsModel;
import com.stanzaliving.api.constants.OTPConstants;
//import com.stanzaliving.api.dao.OTPHistoryDao;
import com.stanzaliving.api.kafka.KafkaUtil;
//import com.stanzaliving.api.util.PatternMatchUtil;

@Service("otpService")
@Transactional
public class OTPServiceImpl implements OTPService {

	@Autowired
	KafkaUtil kafkaUtil;

	private final Logger logger = LoggerFactory.getLogger(OTPServiceImpl.class);

	@Override
	public String generateOTP(int length) {
		String allowedCharsForOtp = OTPConstants.ALLOWED_CHARACTERS;
		String generatedOtp = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			generatedOtp += allowedCharsForOtp.charAt(random.nextInt(allowedCharsForOtp.length()));
		}
		return generatedOtp;
	}

	public void sendOTPMessage(String mobileNumber, String message) {
		SmsModel sms = new SmsModel(mobileNumber, message);
		kafkaUtil.produceSms(sms);
	}

	@Override
	public void sendOTP(String mobileNumber, String otp) {
		sendOTPMessage(mobileNumber, String.format(OTPConstants.OTP_MESSAGE, otp, ""));
	}

	@Override
	public void sendOTP(String mobileNumber) {
		String otp = generateOTP(OTPConstants.OTP_LENGTH);
		sendOTP(mobileNumber, otp);
	}
}
