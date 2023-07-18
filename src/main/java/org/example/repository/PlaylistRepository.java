package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;

import org.example.dto.PlaylistSongsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlaylistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //this.jdbcTemplate.execute("CREATE TABLE playlist (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255)) ");
    }



    public List<PlaylistDTO> selectAll() {
        return jdbcTemplate.query("SELECT * FROM Playlist", new BeanPropertyRowMapper<>(PlaylistDTO.class));
    }

    public PlaylistDTO show(long id) {
        RowMapper<PlaylistDTO> rowMapper = (rs, rowNum) -> {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(rs.getLong("id"));
            playlistDTO.setName(rs.getString("name"));
            return playlistDTO;
        };

        return jdbcTemplate.query("SELECT * FROM Playlist WHERE id=?", rowMapper, id).stream().findFirst().orElse(null);
    }

    public void save(PlaylistDTO playlist) {
        jdbcTemplate.update("INSERT INTO Playlist (name) VALUES(?)", playlist.getName());
    }

    public void update(long id,PlaylistDTO playlistSongs) {
        jdbcTemplate.update("UPDATE Playlist SET name=? WHERE id=?", playlistSongs.getName(),
                 id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Playlist WHERE id=?", id);
    }
}
