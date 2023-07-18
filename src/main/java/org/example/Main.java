package org.example;

import org.example.config.SpringConfig;
import org.example.dto.PlaylistDTO;
import org.example.dto.PlaylistSongsDTO;
import org.example.dto.SingerDTO;
import org.example.dto.SongDTO;
import org.example.repository.PlaylistRepository;
import org.example.repository.SingerRepository;
import org.example.service.MusicService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MusicService musicService = context.getBean(MusicService.class);

        SingerDTO singer1 = SingerDTO.builder().id(1L).name("Lil Zhannurkhan").songName("Redbone").build();
        SingerDTO singer2 = SingerDTO.builder().id(2L).name("Childish Gambino").songName("Zhazda apamnyn auylyna").build();

        //musicService.saveSinger(singer1);
        musicService.saveSinger(singer1);


        SongDTO song1 = SongDTO.builder().id(1L).name("Redbone").singer("Zhanik").build();
        SongDTO song2 = SongDTO.builder().id(2L).name("Zhazda apamnyn auyalyna").singer("Erbo").build();

        PlaylistDTO playlist = PlaylistDTO.builder().id(1L).name("Top playlist").build();

        PlaylistSongsDTO playlistSongs1 = PlaylistSongsDTO.builder().id(1L).playlistId(1L).songId(2L).build();
        PlaylistSongsDTO playlistSongs2 = PlaylistSongsDTO.builder().id(1L).playlistId(1L).songId(1L).build();

        musicService.allSingers();
    }
}
