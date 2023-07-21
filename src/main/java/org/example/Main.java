package org.example;

import jakarta.persistence.Entity;
import org.example.config.SpringConfig;
import org.example.model.Playlist;
import org.example.service.PlaylistService;

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

        for(Playlist playlist: playlistList){
            System.out.println(playlist.getPlaylistName());
        }


    }
}
