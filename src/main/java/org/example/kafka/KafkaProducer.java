package org.example.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.model.Singer;
import org.example.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

    @Component
    public class KafkaProducer {

        private final KafkaTemplate<String, Song> kafkaTemplate;

        private final ObjectMapper objectMapper;

        @Autowired
        public KafkaProducer(KafkaTemplate<String, Song> kafkaTemplate, ObjectMapper objectMapper) {
            this.kafkaTemplate = kafkaTemplate;
            this.objectMapper = objectMapper;
        }

        public void newSong(String topic, Song song) {
                kafkaTemplate.send(topic, song);
        }

    }