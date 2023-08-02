package org.example.service;

import org.example.kafka.KafkaProducer;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KafkaJmsServiceTest {
    @Mock
    KafkaProducer kafkaProducer;
    @InjectMocks
    KafkaJmsService kafkaJmsService;

    private Song song;

    @BeforeEach
    void setUp() {
        Singer singer = Singer.builder().singerId(1L).singerName("Zhannurkhan").genre("Q-pop").country("KZ").build();
        song = Song.builder().songId(1L).songTitle("Bara almadym").singer(singer).playlistSet(new HashSet<>()).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendSong() {
        String topic = "test";

        kafkaJmsService.newSong(topic, song);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);

        verify(kafkaProducer).newSong(topicCaptor.capture(), songCaptor.capture());

        assertEquals(topic, topicCaptor.getValue());
        assertEquals(song.getSongTitle(), songCaptor.getValue().getSongTitle());
        assertEquals(song.getSongId(), songCaptor.getValue().getSongId());
    }
}

