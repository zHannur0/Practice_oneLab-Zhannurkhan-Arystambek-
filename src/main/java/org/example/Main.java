package org.example;

import jakarta.persistence.Entity;
import org.example.config.SpringConfig;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.service.KafkaJmsService;
import org.example.service.PlaylistService;

import org.example.service.SingerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        PlaylistService playlistService = context.getBean(PlaylistService.class);

        List<Playlist> playlistList = playlistService.getAllPlaylists();

        SingerService singerService = context.getBean(SingerService.class);

        List<Singer> singers = singerService.getAllSingers();

        KafkaJmsService kafkaJmsService = context.getBean(KafkaJmsService.class);
        kafkaJmsService.sendUser("test", singers.get(0));

    }
}
