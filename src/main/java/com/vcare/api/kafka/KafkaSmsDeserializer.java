package com.vcare.api.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcare.api.Gateway.SmsModel;

import java.util.Map;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

/**
 *
 * @author vikrantbhosale
 */
public class KafkaSmsDeserializer implements Deserializer {
       @Override
    public void close() {
    }

//    @Override
//    public void configure(Map<String, ?> arg0, boolean arg1) {
//  }
    
   

    @Override
    public SmsModel deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        SmsModel sms = null;
        try {
            sms = mapper.readValue(arg1, SmsModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sms;
    }

    @Override
    public SmsModel deserialize(String string, Headers hdrs, byte[] bytes) {
        return (SmsModel)Deserializer.super.deserialize(string, hdrs, bytes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configure(Map map, boolean bln) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
