package org.example.repository;

import org.example.model.Playlist;
import org.example.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    void countDistinctByPlaylistName(String playlistName);

    List<Playlist> findByPlaylistNameOrderByPlaylistNameAsc(String playlistName);

    void deleteByPlaylistName(String playlistName);

    void deleteBySongsListContaining(Song song);

    long countBySongsListContaining(Song song);


}
