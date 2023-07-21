package org.example.service;

import org.example.mapper.SongMapper;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSingers() {
        return songRepository.findAll();
    }
}
