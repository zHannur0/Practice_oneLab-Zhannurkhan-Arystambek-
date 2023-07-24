package org.example;

import org.example.model.Playlist;
import org.example.service.PlaylistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlaylistServiceTest {

    @Autowired
    private PlaylistService playlistService;

    @Test
    @DisplayName("Test getting all playlists")
    public void testGettingAllPlaylists() {
        List<Playlist> actualPlaylists = playlistService.getAllPlaylists();

        // Assert
        assertNotNull(actualPlaylists, "Getting all playlists failed");
    }

    @Test
    @DisplayName("Test deleting playlist by playlist name")
    public void testDeletingPlaylistByPlaylistName() {
        String playlistNameToDelete = "Top";

        int size1 = playlistService.getAllPlaylists().size();

        playlistService.deletePlaylistByPlaylistName(playlistNameToDelete);

        int size2 = playlistService.getAllPlaylists().size();


        assertNotEquals(size1,size2, "Deleting playlist by name failed");
    }
}
