package org.example.service;

import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PlaylistServiceTest {
    @Mock
    PlaylistRepository playlistRepository;
    @InjectMocks
    PlaylistService playlistService;

    private Playlist playlist1;
    private Playlist playlist2;
    private Song song;

    @BeforeEach
    public void setUp() {
        song = new Song(1L, "Song1", new Singer(), new HashSet<>());

        playlist1 = new Playlist(1L, "Playlist1", new HashSet<>());
        playlist2 = new Playlist(2L, "Playlist2", new HashSet<>());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlaylists() {
        List<Playlist> mockPlaylists = new ArrayList<>();
        mockPlaylists.add(playlist1);
        mockPlaylists.add(playlist2);

        when(playlistRepository.findAll()).thenReturn(mockPlaylists);

        List<Playlist> result = playlistService.getAllPlaylists();

        assertEquals(mockPlaylists, result);
    }

    @Test
    public void testGetPlaylistsByPlaylistNameAsc() {
        List<Playlist> mockPlaylists = new ArrayList<>();
        mockPlaylists.add(playlist1);
        mockPlaylists.add(playlist2);

        when(playlistRepository.findByPlaylistNameOrderByPlaylistNameAsc("Playlist1")).thenReturn(mockPlaylists);

        List<Playlist> result = playlistService.getPlaylistsByPlaylistNameAsc("Playlist1");

        assertEquals(mockPlaylists, result);
    }

    @Test
    public void testGetPlaylistById() {
        Long playlistId = 1L;
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist1));

        Playlist result = playlistService.getPlaylistById(playlistId);

        assertEquals(playlist1, result);
    }

    @Test
    public void testGetPlaylistByIdNotFound() {
        Long playlistId = 999L;
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.empty());

        Playlist result = playlistService.getPlaylistById(playlistId);

        assertNull(result);
    }

    @Test
    public void testDeletePlaylistByPlaylistName() {
        playlistService.deletePlaylistByPlaylistName("Playlist1");

        verify(playlistRepository, times(1)).deleteByPlaylistName("Playlist1");
    }

    @Test
    public void testDeletePlaylistBySongContaining() {
        playlistService.deletePlaylistBySongContaining(song);

        verify(playlistRepository, times(1)).deleteBySongsListContaining(song);
    }

    @Test
    public void testCountPlaylistsBySongContaining() {
        when(playlistRepository.countBySongsListContaining(song)).thenReturn(2L);

        long result = playlistService.countPlaylistsBySongContaining(song);

        assertEquals(2L, result);
    }

    @Test
    public void testSavePlaylist() {
        Playlist playlistToSave = new Playlist(3L, "Playlist3", new HashSet<>());

        playlistService.savePlaylist(playlistToSave);

        verify(playlistRepository, times(1)).save(playlistToSave);
    }

    @Test
    public void testDeletePlaylistById() {
        Long playlistId = 1L;

        playlistService.deletePlaylistById(playlistId);

        verify(playlistRepository, times(1)).deleteById(playlistId);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme