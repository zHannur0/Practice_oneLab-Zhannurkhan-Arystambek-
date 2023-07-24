package org.example.repository;
import org.example.mapper.SongMapper;
import org.example.model.Playlist;
import org.example.model.Singer;
import org.example.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    void deleteBySongTitle(String songTitle);

    List<Song> findBySinger(Singer singer);

    List<Song> findByPlaylistSetContains(Playlist playlist);

    List<Song> findBySongTitleOrderBySongTitleAsc(String songTitle);

    List<Song> findBySingerAndPlaylistSetContains(Singer singer, Playlist playlist);

    void deleteBySinger(Singer singer);

    void deleteByPlaylistSetContains(Playlist playlist);

    long countBySinger(Singer singer);

    long countByPlaylistSetContains(Playlist playlist);

}
