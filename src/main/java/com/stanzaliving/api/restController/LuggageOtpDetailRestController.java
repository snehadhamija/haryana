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

import com.stanzaliving.api.dto.LuggageOtpDetailDto;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.service.LuggageOtpDetailService;
import com.stanzaliving.api.util.LuggageOtpDetailUtil;

@RestController
@RequestMapping("/luggageOtpDetail")
public class LuggageOtpDetailRestController {

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	@Autowired
	LuggageOtpDetailUtil luggageOtpDetailUtil;

	// ----- Retrieve all luggage otp details -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageOtpDetails() {
		List<LuggageOtpDetail> luggageOtpDetails = luggageOtpDetailService.findAllLuggageOtpDetails();
		if (luggageOtpDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageOtpDetails, HttpStatus.OK);
	}

	// ----- Retrieve luggageOtpDetail by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> saveLuggageOtpDetail(HttpServletRequest httpRequest,
			@RequestBody HashMap<String, Object> request) {
		if (luggageOtpDetailUtil.areMandatoryFieldsPresentForSave(request)) {
			LuggageOtpDetailDto luggageOtpDetailDto = new LuggageOtpDetailDto(request);
			Object createOtpHashMap = luggageOtpDetailUtil.saveLuggageOtpDetail(httpRequest, luggageOtpDetailDto);
			if (createOtpHashMap == null) {
				return new ResponseEntity<Object>(HttpStatus.CONFLICT);
			}
			return new ResponseEntity<Object>(createOtpHashMap, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("sentTo and sentBy are mandatory fields to process this request !",
				HttpStatus.CONFLICT);
	}

	// -----Validate Luggage OTP Detail
	// Sample request payload
	// {"luggageOtpDetailId":1,"sentTo":"9906000101","otp": "3132"}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Object> validateVendorOtpDetails(@RequestBody HashMap<String, Object> request) {
		if (luggageOtpDetailUtil.areMandatoryFieldsPresentToValidate(request)) {
			LuggageOtpDetailDto luggageOtpDetailDto = new LuggageOtpDetailDto(request);
			HashMap<String, Object> validationObject = luggageOtpDetailUtil
					.validateLuggageOtpDetail(luggageOtpDetailDto);
			if (validationObject.get("httpStatus").toString().equalsIgnoreCase("409")) {
				return new ResponseEntity<Object>(validationObject.get("body"), HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<Object>(validationObject.get("body"), HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>(
				"luggageOtpDetailId, sentTo and otp are mandatory fields to process this request !",
				HttpStatus.CONFLICT);
	}
}
