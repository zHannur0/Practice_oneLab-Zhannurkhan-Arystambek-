package org.example.service;

import org.example.mapper.SongMapper;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSingers() {
        return songRepository.findAll();
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> getSongsBySinger(Singer singer) {
        return songRepository.findBySinger(singer);
    }

    public List<Song> getSongsByPlaylist(Playlist playlist) {
        return songRepository.findByPlaylistSetContains(playlist);
    }

    public List<Song> getSongsBySongTitleAsc(String songTitle) {
        return songRepository.findBySongTitleOrderBySongTitleAsc(songTitle);
    }

    public List<Song> getSongsBySingerAndPlaylist(Singer singer, Playlist playlist) {
        return songRepository.findBySingerAndPlaylistSetContains(singer, playlist);
    }

    public Song getSongById(long id) {
        return songRepository.findById(id).orElse(null);
    }

    public void deleteSongsBySinger(Singer singer) {
        songRepository.deleteBySinger(singer);
    }

    public void deleteSongsByPlaylist(Playlist playlist) {
        songRepository.deleteByPlaylistSetContains(playlist);
    }

    public long countSongsBySinger(Singer singer) {
        return songRepository.countBySinger(singer);
    }

    public long countSongsByPlaylist(Playlist playlist) {
        return songRepository.countByPlaylistSetContains(playlist);
    }

    @Transactional
    public void saveAllSongs(List<Song> songs) {
        songRepository.saveAll(songs);
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Transactional
    public void deleteAllSongs(List<Long> songIds) {
        songIds.forEach(songRepository::deleteById);
    }

    public void deleteSongById(long id) {
        songRepository.deleteById(id);
    }
}
