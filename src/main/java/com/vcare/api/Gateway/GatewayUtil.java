package com.vcare.api.Gateway;

import com.vcare.api.kafka.KafkaEmailModel;
import com.vcare.api.kafka.KafkaLeadsModel;

/**
 *
 * @author vikrantbhosale
 */
public interface GatewayUtil {

    public void SendEmail(IEmailModel iEmailModel);

    public void CreateLead(ILeadsModel iLeadsModel);

    public void SendSms(ISmsModel iSmsModel);

}
