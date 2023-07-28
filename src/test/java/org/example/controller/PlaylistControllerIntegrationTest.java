package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.utils.Json;
import org.example.Main;
import org.example.model.Playlist;
import org.example.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PlaylistController playlistController;

    @Mock
    private PlaylistService playlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playlistController).build();
    }

    @Test
    public void testGetPlaylists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/playlists")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testCreatePlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setPlaylistName("Test Playlist");
        mockMvc.perform(MockMvcRequestBuilders.post("/playlists/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playlist))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdatePlaylist() throws Exception {
        long playlistId = 1L;
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setPlaylistName("Updated Playlist");
        mockMvc.perform(MockMvcRequestBuilders.put("/playlists/" + playlistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playlist))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeletePlaylist() throws Exception {
        long playlistId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/playlists/" + playlistId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
