package org.example.service;

import org.example.mapper.SingerMapper;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }

    @Transactional
    public void saveAllSingers(List<Singer> singers) {
        singerRepository.saveAll(singers);
    }

    @Transactional
    public void deleteAllSingers(List<Long> singerIds) {
        singerIds.forEach(singerRepository::deleteById);
    }


}
