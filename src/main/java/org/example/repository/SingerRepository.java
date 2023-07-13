package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.SingerDTO;
import org.example.dto.SongDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SingerRepository {
    private final List<SingerDTO> singers = new ArrayList<>();

    public SingerDTO save(SingerDTO author) {
        singers.add(author);
        return author;
    }

    public List<SingerDTO> selectAll() {
        return singers;
    }

    public SingerDTO selectById(Long id) {
        return singers.stream().filter(singer -> singer.getId().equals(id)).findFirst().orElse(null);
    }

    public void addSong(Long singerId, SongDTO song) {
        SingerDTO singer = selectById(singerId);
        singer.addSongs(song);
    }

}
