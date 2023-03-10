package com.artser.kafka.sevice;

import com.artser.kafka.config.AppConstants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

  private static final Logger logger =
      LoggerFactory.getLogger(KafkaProducerService.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    logger.info(String.format("Message sent -> %s", message));
    this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
  }

  public void sendRecord(String message) {
    ProducerRecord<String, String> producerRecord =
        new ProducerRecord<>(AppConstants.TOPIC_NAME, message);
    logger.info(String.format("Record sent -> %s", producerRecord));
    this.kafkaTemplate.send(producerRecord);
  }
}
