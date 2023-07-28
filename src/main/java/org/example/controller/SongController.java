package org.example.controller;


import org.example.model.Song;
import org.example.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/songs")
public class SongController {

    SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Song> getSongs() {
        return songService.getAllSongs();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Song getSong(@PathVariable long id) {
        return songService.getSongById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Song createSong(@RequestBody Song song) {
        songService.saveSong(song);
        return song;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Song updateSong(@PathVariable long id, @RequestBody Song song) {
        song.setSongId(id);
        songService.saveSong(song);
        return song;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteSong(@PathVariable long id) {
        songService.deleteSongById(id);
    }

}
