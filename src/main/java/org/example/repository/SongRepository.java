package org.example.repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.dto.SongDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SongRepository {
    private final List<SongDTO> songs = new ArrayList<>();

    public SongDTO save(SongDTO song) {
        songs.add(song);
        return song;
    }

    public List<SongDTO> selectAll() {
        return songs;
    }

    public SongDTO selectById(Long id) {
        return songs.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateName(Long id, String name) {
        SongDTO song = selectById(id);
        song.setName(name);
    }

    public void deleteById(Long id) {
        songs.removeIf(song -> song.getId().equals(id));
    }



}
