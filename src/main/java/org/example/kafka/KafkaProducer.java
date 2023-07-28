package org.example.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.model.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

    @Component
    public class KafkaProducer {

        private final KafkaTemplate<String, String> kafkaTemplate;

        private final ObjectMapper objectMapper;

        @Autowired
        public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
            this.kafkaTemplate = kafkaTemplate;
            this.objectMapper = objectMapper;
        }

        public void sendUser(String topic, Singer singer) {
            try {
                String singerJson = objectMapper.writeValueAsString(singer);
                kafkaTemplate.send(topic, singerJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }