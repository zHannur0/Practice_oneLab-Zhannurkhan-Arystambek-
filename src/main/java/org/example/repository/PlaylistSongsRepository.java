package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.PlaylistDTO;
import org.example.dto.PlaylistSongsDTO;
import org.example.dto.SingerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaylistSongsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlaylistSongsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        this.jdbcTemplate.execute("CREATE TABLE PlaylistSongs (id BIGINT PRIMARY KEY AUTO_INCREMENT, playlistId BIGINT, songId BIGINT);");
    }

    public List<PlaylistSongsDTO> selectAll() {
        return jdbcTemplate.query("SELECT * FROM PlaylistSongs", new BeanPropertyRowMapper<>(PlaylistSongsDTO.class));
    }

    public PlaylistSongsDTO show(long id) {
        RowMapper<PlaylistSongsDTO> rowMapper = (rs, rowNum) -> {
            PlaylistSongsDTO pSongs = new PlaylistSongsDTO();
            pSongs.setId(rs.getLong("id"));
            pSongs.setPlaylistId(rs.getLong("playlistId"));
            pSongs.setSongId(rs.getLong("songId"));
            return pSongs;
        };

        return jdbcTemplate.query("SELECT * FROM PlaylistSongs WHERE id=?", rowMapper, id).stream().findFirst().orElse(null);
    }

    public void save(PlaylistSongsDTO playlistSongs) {
        jdbcTemplate.update("INSERT INTO PlaylistSongs (playlistId, songId) VALUES(?, ?)", playlistSongs.getPlaylistId(), playlistSongs.getSongId());
    }

    public void update(long id,PlaylistSongsDTO playlistSongs) {
        jdbcTemplate.update("UPDATE PlaylistSongs SET playlistId=?, songId=? WHERE id=?", playlistSongs.getPlaylistId(),
                playlistSongs.getSongId(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM PlaylistSongs WHERE id=?", id);
    }

}
