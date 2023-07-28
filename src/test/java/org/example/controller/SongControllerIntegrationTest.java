package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Song;
import org.example.repository.SingerRepository;
import org.example.service.SingerService;
import org.example.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SongController songController;

    @Mock
    private SongService songService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
    }
    @Test
    public void testGetSongs() throws Exception {
        when(songService.getAllSongs()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/songs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSongById() throws Exception {
        Long songId = 1L;
        Song song = new Song();
        song.setSongId(songId);
        song.setSongTitle("Test Song");

        when(songService.getSongById(songId)).thenReturn(song);

        mockMvc.perform(MockMvcRequestBuilders.get("/songs/{id}", songId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.songId").value(songId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.songTitle").value("Test Song"));
    }

    @Test
    public void testCreateSong() throws Exception {
        Song song = new Song();
        song.setSongTitle("New Song");

        mockMvc.perform(MockMvcRequestBuilders.post("/songs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(song))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.songTitle").value("New Song"));
    }

    @Test
    public void testUpdateSong() throws Exception {
        Long songId = 1L;
        Song song = new Song();
        song.setSongId(songId);
        song.setSongTitle("Updated Song");

        mockMvc.perform(MockMvcRequestBuilders.put("/songs/{id}", songId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(song))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.songId").value(songId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.songTitle").value("Updated Song"));
    }

    @Test
    public void testDeleteSong() throws Exception {
        Long songId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/songs/{id}", songId))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
