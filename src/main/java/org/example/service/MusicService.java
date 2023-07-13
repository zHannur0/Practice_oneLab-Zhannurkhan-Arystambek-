package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.PlaylistDTO;
import org.example.dto.PlaylistSongsDTO;
import org.example.dto.SingerDTO;
import org.example.dto.SongDTO;
import org.example.repository.PlaylistRepository;
import org.example.repository.PlaylistSongsRepository;
import org.example.repository.SingerRepository;
import org.example.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MusicService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistSongsRepository playlistSongsRepository;
    private final SingerRepository singerRepository;
    private final SongRepository songRepository;

    public List<SongDTO> getAllSongs() {
        return songRepository.selectAll();
    }

    public void addNewSong(Long singerId, SongDTO song) {
        songRepository.save(song);
        singerRepository.addSong(singerId, song);
    }

    public void allSingers() {
        List<SingerDTO> singers = singerRepository.selectAll();
        List<String> names = singers.stream().map(SingerDTO::getName).collect(Collectors.toList());

        for(String name: names) {
            System.out.println("Singer " + name);
        }
    }

    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        playlistSongsRepository.deleteSong(playlistId, songId);
    }

    public void setPlaylistName(Long id, String name) {
        playlistRepository.updateName(id, name);
    }

    public void addSong(SongDTO song) {
        System.out.println(songRepository.save(song));
    }

    public void addSinger(SingerDTO singer) {
        System.out.println(singerRepository.save(singer));
    }

    public void addPlaylistSongs(PlaylistSongsDTO playlistSongs) {
        System.out.println(playlistSongsRepository.save(playlistSongs));
    }

    public void addPlaylist(PlaylistDTO playlist) {
        System.out.println(playlistRepository.save(playlist));
    }
}
