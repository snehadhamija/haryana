package com.stanzaliving.api.restController;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.constants.OTPConstants;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.service.LuggageOtpDetailService;
import com.stanzaliving.api.service.OTPService;
import com.stanzaliving.api.util.LuggageOtpDetailUtil;

@RestController
public class LuggageOtpDetailRestController {

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	@Autowired
	OTPService oTPService;

	@Autowired
	LuggageOtpDetailUtil luggageOtpDetailUtil;

	// ----- Retrieve all luggage otp details -----
	@RequestMapping(value = "/luggageOtpDetail", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageOtpDetails() {
		List<LuggageOtpDetail> luggageOtpDetails = luggageOtpDetailService.findAllLuggageOtpDetails();
		if (luggageOtpDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageOtpDetails, HttpStatus.OK);
	}

	// ----- Retrieve luggageOtpDetail by id -----
	@RequestMapping(value = "/luggageOtpDetail/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageOtpDetailById(@PathVariable("id") int id) {
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService.findById(id);
		if (luggageOtpDetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String decodedOtp = luggageOtpDetailUtil.decodeOtp(luggageOtpDetail.getOtp());
		HashMap<String, Object> hashMap = luggageOtpDetailUtil.pupulateStatusHashMap(luggageOtpDetail, decodedOtp);
		return new ResponseEntity<Object>(hashMap, HttpStatus.OK);
	}

	// ----Save Luggage OTP Detail
	// Sample request payload:
	// {"sentTo":"9906000101","sentBy":"9906000101"}
	@RequestMapping(value = "/luggageOtpDetail", method = RequestMethod.POST)
	public ResponseEntity<Object> saveLuggageOtpDetail(HttpServletRequest httpRequest,
			@RequestBody HashMap<String, Object> request) {
		String otp = null;
		String sentTo = null;
		String sentBy = null;
		LuggageOtpDetail luggageOtpDetail = null;
		if (request.containsKey("sentTo")) {
			sentTo = (String) request.get("sentTo");
		}
		if (request.containsKey("sentBy")) {
			sentBy = (String) request.get("sentBy");
		}
		try {
			otp = oTPService.generateOTP(OTPConstants.OTP_LENGTH);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		luggageOtpDetail = luggageOtpDetailUtil.saveLuggageOtpDetailObject(httpRequest, otp, sentTo, sentBy);
		if (luggageOtpDetail == null) {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
		try {
			oTPService.sendOTP(sentTo, otp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> createOtpHashMap = luggageOtpDetailUtil.pupulateCreateOtpHashMap(luggageOtpDetail, otp);
		return new ResponseEntity<Object>(createOtpHashMap, HttpStatus.OK);
	}

	// -----Validate Luggage OTP Detail
	// Sample request payload
	// {"luggageOtpDetailId":"1","sentTo":"9906000101","otp": "3132"}
	@RequestMapping(value = "/luggageOtpDetail/", method = RequestMethod.POST)
	public ResponseEntity<Object> validateVendorOtpDetails(@RequestBody HashMap<String, Object> request) {
		Integer luggageOtpDetailId = null;
		String sentTo = null;
		String otp = null;
		if (request.containsKey("luggageOtpDetailId")) {
			luggageOtpDetailId = (Integer) request.get("luggageOtpDetailId");
		}
		if (request.containsKey("sentTo")) {
			sentTo = (String) request.get("sentTo");
		}
		if (request.containsKey("otp")) {
			otp = (String) request.get("otp");
		}
		if (luggageOtpDetailService.findById(luggageOtpDetailId) == null) {
			return new ResponseEntity<Object>("No record found with the luggageOtpDetailId: " + luggageOtpDetailId,
					HttpStatus.CONFLICT);
		}
		if (luggageOtpDetailService.checkIfOtpExpired(luggageOtpDetailId)) {
			return new ResponseEntity<Object>("OTP already expired !", HttpStatus.CONFLICT);
		}
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService.validateOtp(sentTo, otp, luggageOtpDetailId);
		if (luggageOtpDetail != null) {
			if (luggageOtpDetail.getIsValidated()) {
				return new ResponseEntity<Object>("Already Validated !", HttpStatus.OK);
			}
			luggageOtpDetail = luggageOtpDetailService.validateLuggageOtpDetail(luggageOtpDetail);
			HashMap<String, Object> statusHashMap = luggageOtpDetailUtil.pupulateStatusHashMap(luggageOtpDetail);
			return new ResponseEntity<Object>(statusHashMap, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not validated !", HttpStatus.CONFLICT);
	}

}
