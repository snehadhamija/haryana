package com.vcare.api.Gateway;

public class SmsModel implements ISmsModel {

	// private String mobile;
	// private String message;
	// private String api_key;
	// private int route;
	private String sender = "STANZA";

	private String route;

	private String country = "91";

	private String message;

	private String to;

	private String api_key;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public SmsModel() {

	}

	// public SMSModel(String mobile, String message){
	// this.mobile = mobile;
	// this.message = message;
	// }
	// public SmsModel(String to, String message) {
	// this.message = message;
	// this.to = to;
	// }
	public SmsModel(String to, String message, String route) {
		this.message = message;
		this.to = to;
		this.route = route;
	}

	public SmsModel(String mobileNumber, String message) {
		this.to = mobileNumber;
		this.message = message;
	}

	public String toString() {
		// return "sender:" + " " sender " " + "to:" + " " to + "route" + " "
		// route + "message" + " " message ; }
		return "sender" + sender + " " + "to" + to + " " + "route" + route + "message" + message + " country" + country;
	}

}
