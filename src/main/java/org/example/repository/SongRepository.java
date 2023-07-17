package org.example.repository;
import org.example.dto.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongRepository(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE Song (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), singer VARCHAR(255));");
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SongDTO> selectAll() {
        return jdbcTemplate.query("SELECT * FROM Song", new BeanPropertyRowMapper<>(SongDTO.class));
    }

    public SongDTO show(long id) {
        RowMapper<SongDTO> rowMapper = (rs, rowNum) -> {
            SongDTO songDTO = new SongDTO();
            songDTO.setId(rs.getLong("id"));
            songDTO.setName(rs.getString("name"));
            songDTO.setSinger(rs.getString("singer"));
            return songDTO;
        };

        return jdbcTemplate.query("SELECT * FROM Song WHERE id=?", rowMapper, id).stream().findFirst().orElse(null);
    }

    public void save(SongDTO song) {
        jdbcTemplate.update("INSERT INTO Song VALUES(1, ?, ?)", song.getName(), song.getSinger());
    }

    public void update(long id,SongDTO updatedSong) {
        jdbcTemplate.update("UPDATE Song SET name=?, singer=? WHERE id=?", updatedSong.getName(),
                updatedSong.getSinger(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Song WHERE id=?", id);
    }


}
