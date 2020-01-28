package com.vcare.api.kafka;

import com.vcare.api.Gateway.ISmsModel;

public class KafkaSmsModel implements ISmsModel{

//	private String mobile;
//    private String message;
//    private String api_key;
//    private int route;
    
    
    private String sender;
   
   private String route;
   
   private String country;
   
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

   
        
        public KafkaSmsModel(){
            
        }
        
//        public SMSModel(String mobile, String  message){
//            this.mobile = mobile;
//            this.message = message;
//        }

    public KafkaSmsModel(String message, String to) {
        this.message = message;
        this.to = to;
    }

    
    public String toString() {
//        return "sender:" + " " sender " " + "to:" + " " to + "route" + " " route + "message" + " " message ; }
        return "sender" + sender+" " + "to" +  to+" "+"route" + route+ "message" +  message+ " country" + country;
    }

}
