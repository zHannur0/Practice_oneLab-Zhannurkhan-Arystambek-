package org.example.service;

import org.example.model.Playlist;
import org.example.model.Song;
import org.example.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public List<Playlist> getPlaylistsByPlaylistNameAsc(String playlistName) {
        return playlistRepository.findByPlaylistNameOrderByPlaylistNameAsc(playlistName);
    }

    @Transactional
    public void deletePlaylistByPlaylistName(String playlistName) {
        playlistRepository.deleteByPlaylistName(playlistName);
    }

    @Transactional
    public void deletePlaylistBySongContaining(Song song) {
        playlistRepository.deleteBySongsListContaining(song);
    }

    public long countPlaylistsBySongContaining(Song song) {
        return playlistRepository.countBySongsListContaining(song);
    }

}

