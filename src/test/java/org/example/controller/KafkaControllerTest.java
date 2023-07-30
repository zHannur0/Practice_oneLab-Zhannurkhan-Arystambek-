package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Singer;
import org.example.model.Song;
import org.example.service.KafkaJmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaControllerTest {

    @Mock
    private KafkaJmsService kafkaJmsService;

    @InjectMocks
    private KafkaController kafkaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Singer singer;
    private Song song;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(kafkaController).build();
        objectMapper = new ObjectMapper();
        singer = Singer.builder().singerId(1L).singerName("Zhannurkhan").genre("Q-pop").country("KZ").build();
        song = Song.builder().songId(1L).songTitle("Bara almadym").singer(singer).playlistSet(new HashSet<>()).build();
    }

    @Test
    public void testSendMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/kafka/producer",
                Song.class)).as(song.toString());
    }
}
