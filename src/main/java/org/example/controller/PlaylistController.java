package org.example.controller;


import org.example.mapper.PlaylistMapper;
import org.example.model.Playlist;
import org.example.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {

    PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PlaylistMapper> getPlaylists() {
        return playlistService.getAllPlaylists();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public PlaylistMapper getPlaylist(@PathVariable long id) {
        return playlistService.getPlaylistById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        playlistService.savePlaylist(playlist);
        return playlist;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Playlist updatePlaylist(@PathVariable long id, @RequestBody Playlist playlist) {
        playlist.setPlaylistId(id);
        playlistService.savePlaylist(playlist);
        return playlist;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deletePlaylist(@PathVariable long id) {
        playlistService.deletePlaylistById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{id}/{songId}")
    public void addSongToPlaylist(@PathVariable long id,@PathVariable long songId) {
        playlistService.addSongToPlaylist(songId, id);
    }
}
