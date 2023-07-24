package org.example.repository;

import org.example.model.Singer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SingerRepository extends JpaRepository<Singer, Long> {

    Singer findSingerBySingerNameAndCountry(String singerName, String country);

    List<Singer> findByCountry(String country);

    List<Singer> findByGenreAndCountry(String genre, String country);

    void deleteBySingerName(String singerName);

    void deleteByCountry(String country);

    long countByGenre(String genre);

    long countByCountry(String country);
}
