package org.example.service;

import org.example.mapper.PlaylistMapper;
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

    public List<PlaylistMapper> getAllPlaylists() {
        return playlistMappers(playlistRepository.findAll());
    }

    public List<PlaylistMapper> getPlaylistsByPlaylistNameAsc(String playlistName) {
        return playlistMappers(playlistRepository.findByPlaylistNameOrderByPlaylistNameAsc(playlistName));
    }

    public PlaylistMapper getPlaylistById(long id) {

        return playlistMapper(playlistRepository.findById(id).orElse(null));
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

    private List<PlaylistMapper> playlistMappers(List<Playlist> playlistList) {
        return playlistList.stream().map(
                        playlist -> new PlaylistMapper(playlist.getPlaylistId(),
                                playlist.getPlaylistName()))
                .toList();
    }

    private PlaylistMapper playlistMapper(Playlist playlist) {
        if(playlist == null) return null;
        return new PlaylistMapper(playlist.getPlaylistId(), playlist.getPlaylistName());
    }
}

