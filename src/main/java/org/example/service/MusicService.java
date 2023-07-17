package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    public void allSingers() {
        List<SingerDTO> singers = singerRepository.selectAll();
        List<String> names = singers.stream().map(SingerDTO::getName).collect(Collectors.toList());

        for(String name: names) {
            System.out.println("Singer " + name);
        }
    }

    public SingerDTO getSingerById(long id) {
        return singerRepository.show(id);
    }

    public void saveSinger(SingerDTO singer) {
        singerRepository.save(singer);
    }

    public void updateSinger(long id, SingerDTO updatedSinger) {
        singerRepository.update(id, updatedSinger);
    }

    public void deleteSinger(long id) {
        singerRepository.delete(id);
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.selectAll();
    }

    public SongDTO getSongById(long id) {
        return songRepository.show(id);
    }

    public void saveSong(SongDTO song) {
        songRepository.save(song);
    }

    public void updateSong(long id, SongDTO updatedSong) {
        songRepository.update(id, updatedSong);
    }

    public void deleteSong(long id) {
        songRepository.delete(id);
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistRepository.selectAll();
    }

    public PlaylistDTO getPlaylistById(long id) {
        return playlistRepository.show(id);
    }

    public void savePlaylist(PlaylistDTO playlist) {
        playlistRepository.save(playlist);
    }

    public void updatePlaylist(long id, PlaylistDTO updatedPlaylist) {
        playlistRepository.update(id, updatedPlaylist);
    }

    public void deletePlaylist(long id) {
        playlistRepository.delete(id);
    }


    public List<PlaylistSongsDTO> getAllPlaylistSongs() {
        return playlistSongsRepository.selectAll();
    }

    public PlaylistSongsDTO getPlaylistSongById(long id) {
        return playlistSongsRepository.show(id);
    }

    public void savePlaylistSong(PlaylistSongsDTO playlistSongs) {
        playlistSongsRepository.save(playlistSongs);
    }

    public void updatePlaylistSong(long id, PlaylistSongsDTO updatedPlaylistSongs) {
        playlistSongsRepository.update(id, updatedPlaylistSongs);
    }

    public void deletePlaylistSong(long id) {
        playlistSongsRepository.delete(id);
    }

}
