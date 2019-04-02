package com.stanzaliving.api.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.service.LuggageOtpDetailService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageOtpDetailUtil {

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	@Autowired
	SpringRestClientService springRestClientService;

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
		return hashMap;
	}

	public HashMap<String, Object> pupulateCreateOtpHashMap(LuggageOtpDetail luggageOtpDetail, String decodedOtp) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("luggageOtpDetailId", luggageOtpDetail.getId());
		hashMap.put("sentTo", luggageOtpDetail.getSentTo());
		hashMap.put("otp", decodedOtp);
		return hashMap;
	}
}
