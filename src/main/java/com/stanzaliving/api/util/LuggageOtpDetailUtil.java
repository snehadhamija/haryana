package com.stanzaliving.api.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.Gateway.EmailModel;
import com.stanzaliving.api.Gateway.GatewayUtil;
import com.stanzaliving.api.constants.OTPConstants;
import com.stanzaliving.api.dto.LuggageOtpDetailDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.service.LuggageOtpDetailService;
import com.stanzaliving.api.service.OTPService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageOtpDetailUtil {

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	OTPService oTPService;

	@Autowired
	LuggageOtpDetailUtil luggageOtpDetailUtil;

	@Autowired
	GatewayUtil gatewayUtil;

	public final String LUGGAGE_MAILER = "luggage@stanzaliving.com";

	public boolean areMandatoryFieldsPresentForSave(HashMap<String, Object> request) {
		if (request.containsKey("sentTo") && request.containsKey("sentBy")) {
			return true;
		}
		return false;
	}

	public Object saveLuggageOtpDetail(HttpServletRequest httpRequest, LuggageOtpDetailDto luggageOtpDetailDto) {
		String otp = luggageOtpDetailUtil.generateOtp();
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailUtil.saveLuggageOtpDetailObject(httpRequest, otp,
				luggageOtpDetailDto.getSentTo(), luggageOtpDetailDto.getSentBy());
		if (luggageOtpDetail == null) {
			return null;
		}
		luggageOtpDetailUtil.sendOtp(luggageOtpDetailDto, otp);
		luggageOtpDetailUtil.sendLuggageEmail(httpRequest, luggageOtpDetailDto, otp);
		HashMap<String, Object> createOtpHashMap = luggageOtpDetailUtil.pupulateCreateOtpHashMap(luggageOtpDetail, otp);
		return createOtpHashMap;
	}

	public String generateOtp() {
		String otp = null;
		try {
			otp = oTPService.generateOTP(OTPConstants.OTP_LENGTH);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return otp;
	}

	public LuggageOtpDetail saveLuggageOtpDetailObject(HttpServletRequest httpRequest, String otp, String sentTo,
			String sentBy) {
		Boolean isValidated = false;
		String sentToUserMobile = null;
		String sentByUserMobile = null;
		UserDto sentToUser = null;
		UserDto sentByUser = null;
		try {
			sentToUser = springRestClientService.getUserDtoForOtherUser(httpRequest, sentTo);
			sentToUserMobile = sentToUser.getMobileNo();
			sentByUser = springRestClientService.getUserDtoForOtherUser(httpRequest, sentBy);
			sentByUserMobile = sentByUser.getMobileNo();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String base64Otp = new String(Base64.encodeBase64(otp.getBytes()));
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService.checkIfOtpAlreadySent(sentToUserMobile,
				sentByUserMobile, isValidated);
		Date now = new Date();
		Date validTill = DateUtil.addDaysToDate(now, 1);
		if (luggageOtpDetail == null) {
			luggageOtpDetail = luggageOtpDetailService.saveLuggageOtpDetail(base64Otp, validTill, sentToUserMobile,
					sentByUserMobile);
		} else {
			luggageOtpDetail = luggageOtpDetailService.updateLuggageOtpDetail(luggageOtpDetail, base64Otp, validTill);
		}
		return luggageOtpDetail;
	}

	public void sendOtp(LuggageOtpDetailDto luggageOtpDetailDto, String otp) {
		try {
			oTPService.sendOTP(luggageOtpDetailDto.getSentTo(), otp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String decodeOtp(String otp) {
		byte[] decodedBytes = Base64.decodeBase64(otp);
		String decodedOtp = null;
		try {
			decodedOtp = new String(decodedBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedOtp;
	}

	public HashMap<String, Object> pupulateStatusHashMap(LuggageOtpDetail luggageOtpDetail, String decodedOtp) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("luggageOtpDetailId", luggageOtpDetail.getId());
		hashMap.put("sentTo", luggageOtpDetail.getSentTo());
		hashMap.put("sentBy", luggageOtpDetail.getSentBy());
		hashMap.put("validTill", luggageOtpDetail.getValidTill());
		hashMap.put("otp", decodedOtp);
		return hashMap;
	}

	public HashMap<String, Object> pupulateStatusHashMap(LuggageOtpDetail luggageOtpDetail) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("status", "Validated !");
		hashMap.put("sentTo", luggageOtpDetail.getSentTo());
		hashMap.put("luggageOtpDetailId", luggageOtpDetail.getId());
		return hashMap;
	}

	public HashMap<String, Object> pupulateCreateOtpHashMap(LuggageOtpDetail luggageOtpDetail, String decodedOtp) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("luggageOtpDetailId", luggageOtpDetail.getId());
		hashMap.put("sentTo", luggageOtpDetail.getSentTo());
		hashMap.put("otp", decodedOtp);
		return hashMap;
	}

	public boolean areMandatoryFieldsPresentToValidate(HashMap<String, Object> request) {
		if (request.containsKey("luggageOtpDetailId") && request.containsKey("sentTo") && request.containsKey("otp")) {
			return true;
		}
		return false;
	}

	public HashMap<String, Object> validateLuggageOtpDetail(LuggageOtpDetailDto luggageOtpDetailDto) {
		if (luggageOtpDetailService.findById(luggageOtpDetailDto.getLuggageOtpDetailId()) == null) {
			return statusHashMap(
					"No record found with the luggageOtpDetailId: " + luggageOtpDetailDto.getLuggageOtpDetailId(),
					HttpStatus.CONFLICT);
		}
		if (luggageOtpDetailService.checkIfOtpExpired(luggageOtpDetailDto.getLuggageOtpDetailId())) {
			return statusHashMap("OTP already expired !", HttpStatus.CONFLICT);
		}
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService.validateOtp(luggageOtpDetailDto.getSentTo(),
				luggageOtpDetailDto.getOtp(), luggageOtpDetailDto.getLuggageOtpDetailId());
		if (luggageOtpDetail != null) {
			if (luggageOtpDetail.getIsValidated()) {
				return statusHashMap("Already Validated !", HttpStatus.CONFLICT);
			}
			luggageOtpDetail = luggageOtpDetailService.validateLuggageOtpDetail(luggageOtpDetail);
			HashMap<String, Object> statusHashMap = luggageOtpDetailUtil.pupulateStatusHashMap(luggageOtpDetail);
			return statusHashMap(statusHashMap, HttpStatus.OK);
		}
		return statusHashMap("Not validated !", HttpStatus.CONFLICT);
	}

	public HashMap<String, Object> statusHashMap(Object object, HttpStatus httpStatus) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("body", object);
		hashMap.put("httpStatus", httpStatus);
		return hashMap;
	}

	public void sendLuggageEmail(HttpServletRequest httpRequest, LuggageOtpDetailDto luggageOtpDetailDto, String otp) {
		UserDto sentToUser = springRestClientService.getUserDtoForOtherUser(httpRequest,
				luggageOtpDetailDto.getSentTo());
		String sentToUserEmail = sentToUser.getEmail();
		ArrayList<String> mailersList = new ArrayList<>();
		mailersList.add(sentToUserEmail);
		String emailContent = "";
		emailContent = String.format(OTPConstants.OTP_MESSAGE, otp, "");
		EmailModel em = new EmailModel(LUGGAGE_MAILER, "Stanza Luggage OTP", mailersList, emailContent, null);
		em.setContentType(EmailConfig.HTML_MAIL_CONTENT_TYPE);
		gatewayUtil.SendEmail(em);
	}
}
