package com.vcare.api.Gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/gatewaymode")
public class GatewayController {

    @Autowired
    GatewayUtilImpl gatewayUtilImpl;

    private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

    @RequestMapping(value = "/gateway/setmode/{mode}", method = RequestMethod.GET)
    public ResponseEntity<String> setServiceProviderId(@PathVariable("mode") String mode) {
        logger.info("Gateway Mode Set to {}", mode);
        String KAFKA = Constants.KAFKA;

        if (mode.equalsIgnoreCase(Constants.KAFKA)) {
            GatewayUtilImpl.client = Constants.KAFKA;
        } else {
            GatewayUtilImpl.client = Constants.DIRECT;
        }

        return new ResponseEntity<String>(String.format("Success, SENDER MODE SET TO: %s", mode), HttpStatus.OK);
    }
//    ONLY FOR TESTING DIRECT EMAIL

    @RequestMapping(value = "directemail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendDirect(@RequestBody EmailModel emailModel) {
        logger.info("Sending Email Directly");
        gatewayUtilImpl.SendEmail(emailModel);
        return new ResponseEntity<String>(String.format("Success"), HttpStatus.OK);
    }

    //    ONLY FOR TESTING DIRECT SMS
    @RequestMapping(value = "directsms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendDirect(@RequestBody SmsModel smsModel) {
        logger.info("Sending SMS Directly");
        gatewayUtilImpl.SendSms(smsModel);
        return new ResponseEntity<String>(String.format("Success"), HttpStatus.OK);
    }
}
