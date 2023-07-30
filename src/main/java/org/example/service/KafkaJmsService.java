package org.example.service;


import org.example.kafka.KafkaProducer;
import org.example.model.Singer;
import org.example.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaJmsService {

    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaJmsService (KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void newSong(String topic, Song song) {
        kafkaProducer.newSong(topic, song);
    }
}
