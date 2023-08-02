package org.example.service;

import org.example.mapper.PlaylistMapper;
import org.example.mapper.SingerMapper;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<SingerMapper> getAllSingers() {
        return singerMappers(singerRepository.findAll());
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


    public SingerMapper getSingerById(Long singerId) {
        return singerMapper(singerRepository.findById(singerId).orElse(null));
    }

    public List<SingerMapper> getSingersByCountry(String country) {
        return singerMappers(singerRepository.findByCountry(country));
    }

    public List<Singer> getSingersByGenreAndCountry(String genre, String country) {
        return singerRepository.findByGenreAndCountry(genre, country);
    }

    public long countSingersByGenre(String genre) {
        return singerRepository.countByGenre(genre);
    }

    public void deleteSingerById(Long singerId) {

        singerRepository.deleteById(singerId);
    }

    @Transactional
    public void deleteSingersByCountry(String country) {
        List<Singer> singersToDelete = singerRepository.findByCountry(country);
        singerRepository.deleteAll(singersToDelete);
    }

    public void saveSinger(Singer singer){
        singerRepository.save(singer);
    }


    private List<SingerMapper> singerMappers(List<Singer> singers) {
        return singers.stream().map(
                        singer -> new SingerMapper(singer.getSingerId(),
                                singer.getSingerName(),
                                singer.getGenre(),
                                singer.getCountry()))
                .toList();
    }

    private SingerMapper singerMapper(Singer singer) {
        if(singer == null) return null;
        return new SingerMapper(singer.getSingerId(),
                singer.getSingerName(),
                singer.getGenre(),
                singer.getCountry());
    }

}
