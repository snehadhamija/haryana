package com.vcare.api.Gateway;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("smsUtil")
public class SMSUtil {

    private static final Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    public void SendSms(SmsModel sms) {

        //Your authentication key
        String authkey = "248817A0PmicXzHq05bf8eb4b";
        //Multiple mobiles numbers separated by comma
        String mobiles = sms.getTo();
        //Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = sms.getSender();
        //Your message to send, Add URL encoding here.
        String message = sms.getMessage();
        //define route
        String route = sms.getRoute();

        //Prepare Url
        URLConnection myURLConnection = null;
        URL myURL = null;
        BufferedReader reader = null;

        //encoding message
        String encoded_message = URLEncoder.encode(message);

        //Send SMS API
        String mainUrl = "http://api.msg91.com/api/v2/sendsms?";

        //Prepare parameter string
        StringBuilder sbPostData = new StringBuilder(mainUrl);
        sbPostData.append("authkey=" + authkey);
        sbPostData.append("&mobiles=" + mobiles);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + senderId);

        //final string
        mainUrl = sbPostData.toString();
        try {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null) //print response
            {
                System.out.println(response);
                logger.info("Sending Message to: " + " " + sms.getTo() + " With content: " + sms.getMessage());
                logger.info("Message status to " + sms.getTo() + response);
//                RestTemplate restTemplate = new RestTemplate();
//                SmsReportModel smsReportModel = restTemplate.getForObject("http://api.msg91.com/api/v2/sendsms", SmsReportModel.class);
            }
//            kafkaSmsHook(sms);

            //finally close connection
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
