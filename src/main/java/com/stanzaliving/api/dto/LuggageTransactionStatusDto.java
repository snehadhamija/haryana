package com.stanzaliving.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.stanzaliving.api.util.DateUtil;

public class LuggageTransactionStatusDto {

	private HashMap<String, Object> user = null;
	private String mobileNo = null;
	private Integer totalBoxes = 0;
	private Date expectedDate = null;
	private String expectedDateString = null;
	private String amount = null;
	private Integer luggagePaymentModeId = 0;
	private Integer luggageActivityId = 0;
	private Integer luggageStorageRoomId = 0;
	private List<HashMap<String, Object>> luggageSummary = new ArrayList<>();

	public Integer getLuggageActivityId() {
		return luggageActivityId;
	}

	public void setLuggageActivityId(Integer luggageActivityId) {
		this.luggageActivityId = luggageActivityId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getLuggagePaymentModeId() {
		return luggagePaymentModeId;
	}

	public void setLuggagePaymentModeId(Integer luggagePaymentModeId) {
		this.luggagePaymentModeId = luggagePaymentModeId;
	}

	public Integer getLuggageStorageRoomId() {
		return luggageStorageRoomId;
	}

	public void setLuggageStorageRoomId(Integer luggageStorageRoomId) {
		this.luggageStorageRoomId = luggageStorageRoomId;
	}

	public String getExpectedDateString() {
		return expectedDateString;
	}

	public void setExpectedDateString(String expectedDateString) {
		this.expectedDateString = expectedDateString;
	}

	public HashMap<String, Object> getUser() {
		return user;
	}

	public void setUser(HashMap<String, Object> user) {
		this.user = user;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<HashMap<String, Object>> getLuggageSummary() {
		return luggageSummary;
	}

	public void setLuggageSummary(List<HashMap<String, Object>> luggageSummary) {
		this.luggageSummary = luggageSummary;
	}

	public LuggageTransactionStatusDto(HashMap<String, Object> request) {
		super();
		if (request.containsKey("user"))
			this.user = (HashMap<String, Object>) request.get("user");
		this.mobileNo = (String) this.user.get("mobileNo");
		if (request.containsKey("totalBoxes"))
			this.totalBoxes = (Integer) request.get("totalBoxes");
		if (request.containsKey("expectedDate"))
			this.expectedDateString = (String) request.get("expectedDate");
		Date expectedDate = DateUtil.returnStringInDateFormat(this.expectedDateString);
		this.expectedDate = expectedDate;
		if (request.containsKey("amount"))
			this.amount = (String) request.get("amount");
		if (request.containsKey("luggagePaymentModeId"))
			this.luggagePaymentModeId = (Integer) request.get("luggagePaymentModeId");
		if (request.containsKey("luggageActivityId"))
			this.luggageActivityId = (Integer) request.get("luggageActivityId");
		if (request.containsKey("luggageStorageRoomId"))
			this.luggageStorageRoomId = (Integer) request.get("luggageStorageRoomId");
		if (request.containsKey("luggageSummary"))
			this.luggageSummary = (List<HashMap<String, Object>>) request.get("luggageSummary");
	}

	@Override
	public String toString() {
		return "LuggageTransactionStatusDto [user=" + user + ", mobileNo=" + mobileNo + ", totalBoxes=" + totalBoxes
				+ ", expectedDate=" + expectedDate + ", expectedDateString=" + expectedDateString + ", amount=" + amount
				+ ", luggagePaymentModeId=" + luggagePaymentModeId + ", luggageSummary=" + luggageSummary + "]";
	}

	public LuggageTransactionStatusDto() {
		super();
	}
}
