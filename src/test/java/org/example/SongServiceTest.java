package org.example;

import org.example.model.Singer;
import org.example.model.Song;
import org.example.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SongServiceTest {

    @Autowired
    private SongService songService;

    @Test
    @DisplayName("Test saving all songs")
    public void testSaveAllSongs() {

        int size1 = songService.getAllSongs().size();
        Singer singer = new Singer(1L, "Zhannurkhan Arystambek", "Pop", "Kazakhstan");
        // Arrange
        Song song1 = new Song(10L, "Song 1", singer, new HashSet<>());
        Song song2 = new Song(12L, "Song 2", singer, new HashSet<>());
        List<Song> songsToSave = Arrays.asList(song1, song2);

        songService.saveAllSongs(songsToSave);

        int size2 = songService.getAllSongs().size();

        assertNotEquals(size1,size2, "Deleting playlist by name failed");

    }
}
