package org.example.service;

import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class SongServiceTest {

    @Mock
    SongRepository songRepository;

    @InjectMocks
    SongService songService;

    private Song song1;
    private Song song2;
    private Singer singer;
    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        singer = new Singer(1L, "Zhanik", "Funk", "Kazakhstan", new HashSet<>());
        playlist = new Playlist(1L, "aspanga qaraimyn", new HashSet<>());

        song1 = new Song(1L, "New ", singer, new HashSet<>());
        song2 = new Song(2L, "Song2", singer, new HashSet<>());
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetAllSingers() {
        List<Song> mockSongs = new ArrayList<>();
        mockSongs.add(song1);
        mockSongs.add(song2);

        when(songRepository.findAll()).thenReturn(mockSongs);

        List<Song> result = songService.getAllSingers();

        assertEquals(mockSongs, result);
    }

    @Test
    public void testGetAllSongs() {
        List<Song> mockSongs = new ArrayList<>();
        mockSongs.add(song1);
        mockSongs.add(song2);

        when(songRepository.findAll()).thenReturn(mockSongs);

        List<Song> result = songService.getAllSongs();

        assertEquals(mockSongs, result);
    }

    @Test
    public void testGetSongsBySinger() {
        List<Song> mockSongs = new ArrayList<>();
        mockSongs.add(song1);

        when(songRepository.findBySinger(singer)).thenReturn(mockSongs);

        List<Song> result = songService.getSongsBySinger(singer);

        assertEquals(mockSongs, result);
    }

    @Test
    public void testGetSongsByPlaylist() {
        Set<Song> mockSongs = new HashSet<>();
        mockSongs.add(song1);

        playlist.setSongsList(mockSongs);

        when(songRepository.findByPlaylistSetContains(playlist)).thenReturn(new ArrayList<>(mockSongs));

        List<Song> result = songService.getSongsByPlaylist(playlist);

        assertEquals(new ArrayList<>(mockSongs), result);
    }

    @Test
    public void testGetSongsBySongTitleAsc() {
        List<Song> mockSongs = new ArrayList<>();
        mockSongs.add(song1);
        mockSongs.add(song2);

        when(songRepository.findBySongTitleOrderBySongTitleAsc("Song1")).thenReturn(mockSongs);

        List<Song> result = songService.getSongsBySongTitleAsc("Song1");

        assertEquals(mockSongs, result);
    }

    @Test
    public void testGetSongsBySingerAndPlaylist() {
        List<Song> mockSongs = new ArrayList<>();
        mockSongs.add(song1);

        when(songRepository.findBySingerAndPlaylistSetContains(singer, playlist)).thenReturn(mockSongs);

        List<Song> result = songService.getSongsBySingerAndPlaylist(singer, playlist);

        assertEquals(mockSongs, result);
    }

    @Test
    public void testGetSongById() {
        Long songId = 1L;
        when(songRepository.findById(songId)).thenReturn(Optional.of(song1));

        Song result = songService.getSongById(songId);

        assertEquals(song1, result);
    }

    @Test
    public void testGetSongByIdNotFound() {
        Long songId = 999L;
        when(songRepository.findById(songId)).thenReturn(Optional.empty());

        Song result = songService.getSongById(songId);

        assertNull(result);
    }

    @Test
    public void testDeleteSongsBySinger() {
        songService.deleteSongsBySinger(singer);

        verify(songRepository, times(1)).deleteBySinger(singer);
    }

    @Test
    public void testDeleteSongsByPlaylist() {
        songService.deleteSongsByPlaylist(playlist);

        verify(songRepository, times(1)).deleteByPlaylistSetContains(playlist);
    }

    @Test
    public void testCountSongsBySinger() {
        when(songRepository.countBySinger(singer)).thenReturn(2L);

        long result = songService.countSongsBySinger(singer);

        assertEquals(2L, result);
    }

    @Test
    public void testCountSongsByPlaylist() {
        when(songRepository.countByPlaylistSetContains(playlist)).thenReturn(2L);

        long result = songService.countSongsByPlaylist(playlist);

        assertEquals(2L, result);
    }

    @Test
    public void testSaveAllSongs() {
        List<Song> songsToSave = new ArrayList<>();
        songsToSave.add(new Song(3L, "Song3", singer, new HashSet<>()));

        songService.saveAllSongs(songsToSave);

        verify(songRepository, times(1)).saveAll(songsToSave);
    }

    @Test
    public void testSaveSong() {
        Song songToSave = new Song(3L, "Song3", singer, new HashSet<>());

        songService.saveSong(songToSave);

        verify(songRepository, times(1)).save(songToSave);
    }

    @Test
    public void testDeleteAllSongs() {
        List<Long> songIdsToDelete = List.of(1L, 2L);

        songService.deleteAllSongs(songIdsToDelete);

        verify(songRepository, times(1)).deleteById(1L);
        verify(songRepository, times(1)).deleteById(2L);
    }

    @Test
    public void testDeleteSongById() {
        Long songId = 1L;

        songService.deleteSongById(songId);

        verify(songRepository, times(1)).deleteById(songId);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme