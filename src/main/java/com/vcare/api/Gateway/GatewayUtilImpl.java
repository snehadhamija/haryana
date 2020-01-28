package com.vcare.api.Gateway;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcare.api.kafka.KafkaUtil;

@Service("gatewayUtil")
public class GatewayUtilImpl implements GatewayUtil {

    @Autowired
    KafkaUtil kafkaUtil;

    @Autowired
    EmailUtil emailUtil;
    
    @Autowired 
    SMSUtil smsUtil;

    public static String client = Constants.KAFKA;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GatewayUtilImpl.class);

    public void SendEmail(IEmailModel emailModel) {
        if (client.equals(Constants.KAFKA)) {
            System.out.println("email model: " + emailModel);
            kafkaUtil.produceEmail((IEmailModel) emailModel);
        } else if (client.equals(Constants.DIRECT)) {
            logger.info("SENDING EMAIL's DIRECTLY (NOT THROUGH KAFKA");
            EmailModel em = (EmailModel) emailModel;
            emailUtil.send(em);

        }

    }

    public void CreateLead(ILeadsModel iLeadsModel) {
        if (client.equals("KAFKA")) {

            kafkaUtil.produceLead(iLeadsModel);
        }

    }

    public void SendSms(ISmsModel iSmsModel) {
        if (client.equals(Constants.KAFKA)) {
            kafkaUtil.produceSms(iSmsModel);
        } else if (client.equals(Constants.DIRECT)) {
            logger.info("SENDING SMS's DIRECTLY (NOT THROUGH KAFKA");
            SmsModel smsModel = (SmsModel)iSmsModel;
            smsUtil.SendSms(smsModel);
        }
    }

}
