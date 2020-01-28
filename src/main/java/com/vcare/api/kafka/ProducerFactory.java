
package com.vcare.api.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.ser.std.StringSerializer;

/**
 *
 * @author vikrantbhosale
 */
@Service("producerFactory")
public class ProducerFactory {
	private static Map<String, Producer> prodInstanceMap = new HashMap<String, Producer>();

	public Properties getProperty(String producerName) {

		Properties props = new Properties();

		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,

				// "52.66.13.127:9092");
				// "52.66.13.127:9092,13.233.49.174:9092");
				KafkaProperties.BOOTSTRAP_SERVERS);

		props.put(ProducerConfig.CLIENT_ID_CONFIG, producerName);
		// "DemoProducer");
		props.put(ProducerConfig.ACKS_CONFIG, "1");
		// props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
		// IntegerSerializer.class.getName());
		// props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
		// StringSerializer.class.getName());

		// props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		// "hostname1:port1,hostname2:port2,hostname3:port3");
		// Enable a few useful properties for this example. Use of these
		// settings will depend on your particular use case.
		// props.setProperty(ProducerConfig.ACKS_CONFIG, "1");
		// Required properties to process records
		// props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
		// StringSerializer.class.getName());
		// props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
		// LeadSerializer.class.getName() );

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerializer.class.getName());

		// StringSerializer.class.getName());

		// props.put("partitioner.class",
		// "com.stanzaliving.api.kafka.FreshSalesPartitioner");

		return props;

	}

	// public ProducerFactory(){}
	public Producer getProducer() {

		return null;
		// return new Producer<>(configs, keySerializer, valueSerializer);

	}

	public Producer getProducer(String producerName) {

		if (prodInstanceMap.containsKey(producerName))
			return prodInstanceMap.get(producerName);
		Properties props = getProperty(producerName);
		// Producer<String, String> producer;
		// producer = new KafkaProducer<String, String>(props);
		Producer<String, Object> producer;
		producer = new KafkaProducer<String, Object>(props);
		prodInstanceMap.put(producerName, producer);
		return producer;

		// return new Producer<>(configs, keySerializer, valueSerializer);
	}

	@PreDestroy
	public void gracefullDestroy() {

		prodInstanceMap.entrySet().parallelStream().forEach(e -> {
			new Thread(() -> {
				Producer p = e.getValue();
				p.flush();
				p.close(10, TimeUnit.SECONDS);
			}).start();
		});

	}

}
