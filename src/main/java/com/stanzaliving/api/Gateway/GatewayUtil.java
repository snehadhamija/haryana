package com.stanzaliving.api.Gateway;

import com.stanzaliving.api.kafka.KafkaEmailModel;
import com.stanzaliving.api.kafka.KafkaLeadsModel;

/**
 *
 * @author vikrantbhosale
 */
public interface GatewayUtil {

    public void SendEmail(IEmailModel iEmailModel);

    public void CreateLead(ILeadsModel iLeadsModel);

    public void SendSms(ISmsModel iSmsModel);

}
