package org.example.kafka;

import org.example.model.Singer;
import org.example.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    @KafkaListener(topics = "test", groupId = "myGroup")
    public Song listen(Song song) {
        return song;
    }
}
