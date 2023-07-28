package org.example.service;

import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    private List<Singer> allSingers;

    @BeforeEach
    void setUp() {
        singer1 = new Singer(1L, "Singer1", "Genre1", "Country1", new HashSet<>());
        singer2 = new Singer(2L, "Singer2", "Genre2", "Country2", new HashSet<>());
        allSingers = new ArrayList<>();
        allSingers.add(singer1);
        allSingers.add(singer2);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSingers() {
        when(singerRepository.findAll()).thenReturn(allSingers);

        List<Singer> result = singerService.getAllSingers();

        assertEquals(allSingers, result);
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

        Singer result = singerService.getSingerById(singerId);

        assertEquals(singer1, result);
    }

    @Test
    public void testGetSingerByIdNotFound() {
        Long singerId = 999L;
        when(singerRepository.findById(singerId)).thenReturn(Optional.empty());

        Singer result = singerService.getSingerById(singerId);

        assertNull(result);
    }

    @Test
    public void testGetSingersByCountry() {
        String country = "Country1";
        when(singerRepository.findByCountry(country)).thenReturn(List.of(singer1));

        List<Singer> result = singerService.getSingersByCountry(country);

        assertEquals(List.of(singer1), result);
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

