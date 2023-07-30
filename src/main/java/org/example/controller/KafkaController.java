package org.example.controller;

import org.example.kafka.KafkaProducer;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.service.KafkaJmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    KafkaJmsService kafkaService;

    @Autowired
    public KafkaController(KafkaJmsService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping(value = "/newSong")
    public String sendMessage(@RequestBody Song song)
    {
        kafkaService.newSong("test", song);
        return "Message sent Successfully to the topic";
    }
}
