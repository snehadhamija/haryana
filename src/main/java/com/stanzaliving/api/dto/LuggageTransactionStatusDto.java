package com.stanzaliving.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.stanzaliving.api.util.DateUtil;

public class LuggageTransactionStatusDto {

	private String userMobile = null;
	private Integer totalBoxes = 0;
	private Date expectedDate = null;
	private String expectedDateString = null;
	private String amountToBePaid = null;
	private Integer paymentModeId = 0;
	private List<HashMap<String, Object>> luggageSummary = new ArrayList<>();

	public Integer getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Integer paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getExpectedDateString() {
		return expectedDateString;
	}

	public void setExpectedDateString(String expectedDateString) {
		this.expectedDateString = expectedDateString;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Integer getTotalBoxes() {
		return totalBoxes;
	}

	public void setTotalBoxes(Integer totalBoxes) {
		this.totalBoxes = totalBoxes;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(String amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	public List<HashMap<String, Object>> getLuggageSummary() {
		return luggageSummary;
	}

	public void setLuggageSummary(List<HashMap<String, Object>> luggageSummary) {
		this.luggageSummary = luggageSummary;
	}

	public LuggageTransactionStatusDto(HashMap<String, Object> request) {
		super();
		if (request.containsKey("userMobile"))
			this.userMobile = (String) request.get("userMobile");
		if (request.containsKey("totalBoxes"))
			this.totalBoxes = (Integer) request.get("totalBoxes");
		if (request.containsKey("expectedDate"))
			this.expectedDateString = (String) request.get("expectedDate");
		Date expectedDate = DateUtil.returnStringInDateFormat(this.expectedDateString);
		this.expectedDate = expectedDate;
		if (request.containsKey("amountToBePaid"))
			this.amountToBePaid = (String) request.get("amountToBePaid");
		// if (request.containsKey("luggageSummary"))

	}

	public LuggageTransactionStatusDto() {
		super();
	}
}
