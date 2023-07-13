package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PlaylistRepository {
    private final List<PlaylistDTO> playlists = new ArrayList<>();

    public PlaylistDTO save(PlaylistDTO playlist) {
        playlists.add(playlist);
        return playlist;
    }

    public List<PlaylistDTO> selectAll() {
        return playlists;
    }

    public PlaylistDTO selectById(Long id) {
        return playlists.stream().filter(playlist -> playlist.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateName(Long id, String name) {
        PlaylistDTO playlist = selectById(id);
        playlist.setName(name);
    }
}
