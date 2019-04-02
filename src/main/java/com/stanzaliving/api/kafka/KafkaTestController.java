package com.stanzaliving.api.kafka;

import com.stanzaliving.api.Gateway.GatewayUtil;
import com.stanzaliving.api.Gateway.ISmsModel;
import com.stanzaliving.api.Gateway.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vikrantbhosale
 */
@Controller
public class KafkaTestController {
    
    @Autowired
    KafkaUtil kafkaUtil;
    
    @Autowired
    GatewayUtil gatewayUtil;
    
    @RequestMapping(value = "/kafkatestlead", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> leadTest(@RequestBody KafkaLeadsModel leads) {
        
//        System.out.println("lead : " + leads);
//        KafkaSerializer ks = new KafkaSerializer();
//        byte[] ser = ks.serialize("", leads);
//        System.out.println("ser : " + ser);
//        KafkaLeadDeserializer kld = new KafkaLeadDeserializer();
//        Leads out = kld.deserialize("", ser);
//        
//        System.out.println("out : " + out);

//        kafkaUtil.produceLead(leads);
        gatewayUtil.CreateLead(leads);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/kafkatestsms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> smsTest(@RequestBody KafkaSmsModel smsModel) {

//        System.out.println("sms : "+smsModel);
//        KafkaSerializer ks = new KafkaSerializer();
//        byte[] ser = ks.serialize("", smsModel);
//        System.out.println("ser : "+ ser);
//        KafkaSmsDeserializer kd = new KafkaSmsDeserializer();
//        SMSModel out = kd.deserialize("", ser);
//        
//        System.out.println("out : "+ out);
//        kafkaUtil.produceSms(smsModel);
        gatewayUtil.SendSms(smsModel);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/kafkatestemail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> kaftaTest(@RequestBody KafkaEmailModel emailModel) {
        
        System.out.println("email : " + emailModel);
        KafkaSerializer ks = new KafkaSerializer();
        byte[] ser = ks.serialize("", emailModel);
        System.out.println("ser : " + ser);
//        KafkaEmailDeserializer kds = new KafkaEmailDeserializer();
        KafkaEmailDeserializer ked = new KafkaEmailDeserializer();
        
        EmailModel out = ked.deserialize("", ser);
        System.out.println("out : " + out);

//        kafkaUtil.produceEmail(emailModel);
        gatewayUtil.SendEmail(emailModel);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
}
