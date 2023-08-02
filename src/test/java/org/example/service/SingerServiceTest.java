package org.example.service;

import org.example.mapper.SingerMapper;
import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class SingerServiceTest {

    @Mock
    SingerRepository singerRepository;
    @InjectMocks
    SingerService singerService;

    private Singer singer1;
    private Singer singer2;
    private SingerMapper singerMapper1;
    private SingerMapper singerMapper2;

    private List<Singer> allSingers;
    private List<SingerMapper> singerMappers;

    @BeforeEach
    void setUp() {
        singer1 = new Singer(1L, "Singer1", "Genre1", "Country1", new HashSet<>());
        singer2 = new Singer(2L, "Singer2", "Genre2", "Country2", new HashSet<>());
        singerMapper1 = new SingerMapper(1L, "Singer1", "Genre1", "Country1");
        singerMapper2 = new SingerMapper(2L, "Singer2", "Genre2", "Country2");
        allSingers = new ArrayList<>();
        allSingers.add(singer1);
        allSingers.add(singer2);

        singerMappers = new ArrayList<>();
        singerMappers.add(singerMapper1);
        singerMappers.add(singerMapper2);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSingers() {
        when(singerRepository.findAll()).thenReturn(allSingers);

        List<SingerMapper> result = singerService.getAllSingers();

        assertEquals(singerMappers, result);
    }

    @Test
    public void testSaveAllSingers() {
        List<Singer> singersToSave = new ArrayList<>();
        singersToSave.add(new Singer(3L, "Singer3", "Genre3", "Country3",new HashSet<>()));

        singerService.saveAllSingers(singersToSave);

        verify(singerRepository, times(1)).saveAll(singersToSave);
    }

    @Test
    public void testDeleteAllSingers() {
        List<Long> singerIdsToDelete = new ArrayList<>();
        singerIdsToDelete.add(1L);
        singerIdsToDelete.add(2L);

        singerService.deleteAllSingers(singerIdsToDelete);

        verify(singerRepository, times(1)).deleteById(1L);
        verify(singerRepository, times(1)).deleteById(2L);
    }

    @Test
    public void testCountSingersByCountry() {
        String country = "Country1";
        when(singerRepository.countByCountry(country)).thenReturn(2L);

        long result = singerService.countSingersByCountry(country);

        assertEquals(2L, result);
    }

    @Test
    public void testGetSingerById() {
        Long singerId = 1L;
        when(singerRepository.findById(singerId)).thenReturn(Optional.of(singer1));

        SingerMapper result = singerService.getSingerById(singerId);

        assertEquals(singerMapper1, result);
    }

    @Test
    public void testGetSingerByIdNotFound() {
        Long singerId = 999L;
        when(singerRepository.findById(singerId)).thenReturn(Optional.empty());

        SingerMapper result = singerService.getSingerById(singerId);

        assertNull(result);
    }

    @Test
    public void testGetSingersByCountry() {
        String country = "Country1";
        when(singerRepository.findByCountry(country)).thenReturn(List.of(singer1));

        List<SingerMapper> result = singerService.getSingersByCountry(country);

        assertEquals(List.of(singerMapper1), result);
    }

    @Test
    public void testGetSingersByGenreAndCountry() {
        String genre = "Genre1";
        String country = "Country1";
        when(singerRepository.findByGenreAndCountry(genre, country)).thenReturn(List.of(singer1));

        List<Singer> result = singerService.getSingersByGenreAndCountry(genre, country);

        assertEquals(List.of(singer1), result);
    }

    @Test
    public void testCountSingersByGenre() {
        String genre = "Genre1";
        when(singerRepository.countByGenre(genre)).thenReturn(1L);

        long result = singerService.countSingersByGenre(genre);

        assertEquals(1L, result);
    }

    @Test
    public void testDeleteSingerById() {
        Long singerId = 1L;

        singerService.deleteSingerById(singerId);

        verify(singerRepository, times(1)).deleteById(singerId);
    }

    @Test
    public void testDeleteSingersByCountry() {
        String country = "Country1";
        when(singerRepository.findByCountry(country)).thenReturn(List.of(singer1, singer2));

        singerService.deleteSingersByCountry(country);

        verify(singerRepository, times(1)).deleteAll(List.of(singer1, singer2));
    }

    @Test
    public void testSaveSinger() {
        Singer singerToSave = new Singer(3L, "Singer3", "Genre3", "Country3", new HashSet<>());

        singerService.saveSinger(singerToSave);

        verify(singerRepository, times(1)).save(singerToSave);
    }
}

