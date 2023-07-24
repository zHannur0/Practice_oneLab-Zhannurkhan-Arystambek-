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

    public long countSingersByCountry(String country) {
        return singerRepository.countByCountry(country);
    }


    public Singer getSingerById(Long singerId) {
        return singerRepository.findById(singerId).orElse(null);
    }

    public List<Singer> getSingersByCountry(String country) {
        return singerRepository.findByCountry(country);
    }

    public List<Singer> getSingersByGenreAndCountry(String genre, String country) {
        return singerRepository.findByGenreAndCountry(genre, country);
    }

    public long countSingersByGenre(String genre) {
        return singerRepository.countByGenre(genre);
    }


    @Transactional
    public void deleteSingerById(Long singerId) {
        singerRepository.deleteById(singerId);
    }

    @Transactional
    public void deleteSingersByCountry(String country) {
        List<Singer> singersToDelete = singerRepository.findByCountry(country);
        singerRepository.deleteAll(singersToDelete);
    }

}
