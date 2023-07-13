package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.PlaylistDTO;
import org.example.dto.PlaylistSongsDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class PlaylistSongsRepository {
    private final List<PlaylistSongsDTO> playlistSongs = new ArrayList<>();

    public PlaylistSongsDTO save(PlaylistSongsDTO playlistSong) {
        playlistSongs.add(playlistSong);
        return playlistSong;
    }

    public List<PlaylistSongsDTO> selectAll() {
        return playlistSongs;
    }

    public PlaylistSongsDTO selectById(Long id) {
        return playlistSongs.stream().filter(playlistSongsDTO -> playlistSongsDTO.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteSong(Long playlistId, Long songId) {
        playlistSongs.removeIf(playlistSongsDTO -> playlistSongsDTO.getPlaylistId().equals(playlistId) &&
                playlistSongsDTO.getSongId().equals(songId));
    }
}
