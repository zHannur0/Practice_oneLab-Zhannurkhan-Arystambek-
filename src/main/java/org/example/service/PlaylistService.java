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

    private final SongService songService;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, SongService songService) {
        this.playlistRepository = playlistRepository;
        this.songService = songService;
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public List<Playlist> getPlaylistsByPlaylistNameAsc(String playlistName) {
        return playlistRepository.findByPlaylistNameOrderByPlaylistNameAsc(playlistName);
    }

    public Playlist getPlaylistById(long id) {

        return playlistRepository.findById(id).orElse(null);
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

    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void deletePlaylistById(long id) {
        playlistRepository.deleteById(id);
    }

    public void addSongToPlaylist(Long songId, Long playlistId) {
        Song song = songService.getSongById(songId);
        Playlist playlist = playlistRepository.getPlaylistByPlaylistId(playlistId);

        playlist.getSongsList().add(song);

        playlistRepository.save(playlist);
    }

}

