package org.example.controller;


import org.example.mapper.SingerMapper;
import org.example.model.Singer;
import org.example.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/singers")
public class SingerController {

    SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Singer> getSingers() {
        return singerService.getAllSingers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Singer getSinger(@PathVariable long id) {
        return singerService.getSingerById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Singer createSinger(@RequestBody Singer singer) {
        singerService.saveSinger(singer);
        return singer;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Singer updateSinger(@PathVariable long id, @RequestBody Singer singer) {
        singer.setSingerId(id);
        singerService.saveSinger(singer);
        return singer;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteSinger(@PathVariable long id) {
        singerService.deleteSingerById(id);
    }

}
