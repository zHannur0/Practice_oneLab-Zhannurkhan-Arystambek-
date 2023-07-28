package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.example.service.PlaylistService;
import org.example.service.SingerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class SingerControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SingerController singerController;

    @Mock
    private SingerService singerService;

    @Mock
    SingerRepository singerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(singerController).build();
    }

    @Test
    public void testGetSingers() throws Exception {
        when(singerService.getAllSingers()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/singers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSingerById() throws Exception {
        long singerId = 1L;
        when(singerService.getSingerById(singerId)).thenReturn(new Singer());

        mockMvc.perform(MockMvcRequestBuilders.get("/singers/" + singerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateSinger() throws Exception {
        Singer singer = new Singer();
        singer.setSingerName("Test Singer");

        mockMvc.perform(MockMvcRequestBuilders.post("/singers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(singer))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateSinger() throws Exception {
        long singerId = 1L;
        Singer singer = new Singer();
        singer.setSingerId(singerId);
        singer.setSingerName("Updated Singer");


        mockMvc.perform(MockMvcRequestBuilders.put("/singers/" + singerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(singer))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteSinger() throws Exception {
        long singerId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/singers/" + singerId)
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
