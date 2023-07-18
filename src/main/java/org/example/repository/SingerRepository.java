package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.dto.SingerDTO;
import org.example.dto.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class SingerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SingerRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
       // this.jdbcTemplate.execute("CREATE TABLE Singer (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), songName VARCHAR(255));");
    }

    public List<SingerDTO> selectAll() {
        return jdbcTemplate.query("SELECT * FROM Singer", new BeanPropertyRowMapper<>(SingerDTO.class));
    }

    public SingerDTO show(long id) {
        RowMapper<SingerDTO> rowMapper = (rs, rowNum) -> {
            SingerDTO singerDTO = new SingerDTO();
            singerDTO.setId(rs.getLong("id"));
            singerDTO.setName(rs.getString("name"));
            singerDTO.setSongName(rs.getString("songName"));
            return singerDTO;
        };

        return jdbcTemplate.query("SELECT * FROM Singer WHERE id=?", rowMapper, id).stream().findFirst().orElse(null);
    }

    public void save(SingerDTO singer) {
        jdbcTemplate.update("INSERT INTO Singer (name, songName) VALUES(?, ?)", singer.getName(), singer.getSongName());
    }

    public void update(long id,SingerDTO updatedSinger) {
        jdbcTemplate.update("UPDATE Singer SET name=?, songname=? WHERE id=?", updatedSinger.getName(),
                updatedSinger.getSongName(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Singer WHERE id=?", id);
    }

}
