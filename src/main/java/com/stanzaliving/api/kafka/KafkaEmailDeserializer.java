/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanzaliving.api.kafka;

import java.util.Map;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stanzaliving.api.Gateway.EmailModel;

/**
 *
 * @author vikrantbhosale
 */
public class KafkaEmailDeserializer implements Deserializer {
    @Override
    public void close() {
    }

   

    @Override
    public EmailModel deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        EmailModel email = null;
        try {
            email = mapper.readValue(arg1, EmailModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public EmailModel deserialize(String string, Headers hdrs, byte[] bytes) {
        return (EmailModel)Deserializer.super.deserialize(string, hdrs, bytes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configure(Map map, boolean bln) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
