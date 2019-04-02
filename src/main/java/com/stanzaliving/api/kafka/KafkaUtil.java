package com.stanzaliving.api.kafka;

import com.stanzaliving.api.Gateway.IEmailModel;
import com.stanzaliving.api.Gateway.ILeadsModel;
import com.stanzaliving.api.Gateway.ISmsModel;
import com.stanzaliving.api.Gateway.EmailModel;
import com.stanzaliving.api.Gateway.SmsModel;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vikrantbhosale
 */
@Service("kafkaUtil")
public class KafkaUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaUtil.class);
    private static int leadNo = 1;
    private static int messageNo = 1;
    private static int emailNo = 1;

    @Autowired
    ProducerFactory producerFactory;

    public void produceLead(ILeadsModel leads) {
        System.out.println("Inside kafka Lead hook............");
        Date d1 = new Date();
        Producer p;
        p = producerFactory.getProducer("LeadProducer");
        System.out.println("HHH");
        Date d2 = new Date();
        System.out.println("time lag for Lead producer creation : " + (d2.getTime() - d1.getTime()));
        logger.info("time lag for Lead producer creation : " + (d2.getTime() - d1.getTime()));

        String messageStr = "" + leads;
        long startTime = System.currentTimeMillis();
        boolean isAsync = false;
        String topic = KafkaProperties.LEAD_TOPIC;

        if (isAsync) { // Send asynchronously
            p.send(new ProducerRecord<>(topic,
                    leadNo + "",
                    leads), new DemoCallBack(startTime, leadNo, messageStr));
        } else { // Send synchronously
            try {
                p.send(new ProducerRecord<>(topic,
                        leadNo + "",
                        leads)).get();
                System.out.println("Sent message: (" + leadNo + ", " + messageStr + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        ++leadNo;

    }

    public void produceSms(ISmsModel sms) {
        System.out.println("Inside kafka SMS hook............");
        Date d1 = new Date();
//        ProducerFactory pf = new ProducerFactory();
        Producer p;
        p = producerFactory.getProducer("SmsProducer");
        System.out.println("HHH");
        Date d2 = new Date();
        System.out.println("time lag for SMS producer creation : " + (d2.getTime() - d1.getTime()));
        logger.info("time lag for SMS producer creation : " + (d2.getTime() - d1.getTime()));
        String messageStr = "" + sms;
        long startTime = System.currentTimeMillis();
        boolean isAsync = false;
        String topic = KafkaProperties.SMS_TOPIC;
//            		"testSms5";
        if (isAsync) { // Send asynchronously
            p.send(new ProducerRecord<>(topic,
                    messageNo + "",
                    sms), new com.stanzaliving.api.kafka.DemoCallBack(startTime, messageNo, messageStr));
        } else { // Send synchronously
            try {
                p.send(new ProducerRecord<>(topic,
                        messageNo + "",
                        sms)).get();
                System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        ++messageNo;

    }

    public void produceEmail(IEmailModel newEmailModel) {
        System.out.println("Inside kafka Email hook............");
        Date d1 = new Date();
//        ProducerFactory pf = new ProducerFactory();
        Producer p;
        p = producerFactory.getProducer("Emailproducer");
        System.out.println("HHH");
        Date d2 = new Date();
        System.out.println("time lag for Email producer creation : " + (d2.getTime() - d1.getTime()));
        logger.info("time lag for Email producer creation : " + (d2.getTime() - d1.getTime()));
//        ProducerRecord<String, String> data = new ProducerRecord<String, String>(
//                //	        		"ufo_sightings"
//                "test2",
//                 lead.getEmail(), lead.toString());

//        ProducerRecord<String, Object> data = new ProducerRecord<String, Object>(
//                //	        		"ufo_sightings"
//                "leadTest1",
//                 lead.getEmail(), lead);
//        
//        p.send(data);
//        int messageNo = 1;
//        while (true) {
        String emailStr = "" + newEmailModel;
        long startTime = System.currentTimeMillis();
        boolean isAsync = false;
        String topic = KafkaProperties.EMAIL_TOPIC;
//					"testEmail1";
        if (isAsync) { // Send asynchronously
            p.send(new ProducerRecord<>(topic,
                    messageNo + "",
                    newEmailModel), new DemoCallBack(startTime, emailNo, emailStr));
        } else { // Send synchronously
            try {
                p.send(new ProducerRecord<>(topic,
                        messageNo + "",
                        newEmailModel)).get();
                System.out.println("Sent email: (" + emailNo + ", " + emailStr + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        ++messageNo;
    }
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling
     * of request completion. This method will be called when the record sent to
     * the server has been acknowledged. Exactly one of the arguments will be
     * non-null.
     *
     * @param metadata The metadata for the record that was sent (i.e. the
     * partition and offset). Null if an error occurred.
     * @param exception The exception thrown during processing of this record.
     * Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition()
                    + "), "
                    + "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
