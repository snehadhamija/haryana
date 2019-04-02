package com.stanzaliving.api.dto;

import java.util.HashMap;

public class LuggageOtpDetailDto {

	private String sentTo = null;
	private String sentBy = null;
	private String otp = null;
	private Integer luggageOtpDetailId = 0;

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Integer getLuggageOtpDetailId() {
		return luggageOtpDetailId;
	}

	public void setLuggageOtpDetailId(Integer luggageOtpDetailId) {
		this.luggageOtpDetailId = luggageOtpDetailId;
	}

	@Override
	public String toString() {
		return "LuggageOtpDetailDto [sentTo=" + sentTo + ", sentBy=" + sentBy + ", otp=" + otp + ", luggageOtpDetailId="
				+ luggageOtpDetailId + "]";
	}

	public LuggageOtpDetailDto(String sentTo, String sentBy, String otp, Integer luggageOtpDetailId) {
		super();
		this.sentTo = sentTo;
		this.sentBy = sentBy;
		this.otp = otp;
		this.luggageOtpDetailId = luggageOtpDetailId;
	}

	public LuggageOtpDetailDto() {
		super();
	}

	public LuggageOtpDetailDto(HashMap<String, Object> request) {
		super();
		if (request.containsKey("sentTo"))
			this.sentTo = (String) request.get("sentTo");
		if (request.containsKey("sentBy"))
			this.sentBy = (String) request.get("sentBy");
		if (request.containsKey("luggageOtpDetailId"))
			luggageOtpDetailId = (Integer) request.get("luggageOtpDetailId");
		if (request.containsKey("otp"))
			otp = (String) request.get("otp");
	}
}
