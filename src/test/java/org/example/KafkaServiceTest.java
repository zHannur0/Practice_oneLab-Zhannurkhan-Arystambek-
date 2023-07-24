package org.example;

import org.example.kafka.KafkaProducer;
import org.example.model.Singer;
import org.example.service.KafkaJmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class KafkaServiceTest {

    @Mock
    KafkaProducer kafkaProducer;

    @InjectMocks
    private KafkaJmsService kafkaJmsService;

    @Test
    @DisplayName("Test sending user to Kafka")
    public void testSendUser() {
        // Arrange
        String topic = "test-topic";
        Singer singer = Singer.builder().singerId(10L).singerName("Taylor").genre("Hip-hop").country("USA").build();

        kafkaJmsService.sendUser(topic, singer);

        verify(kafkaProducer, times(1)).sendUser(eq(topic), eq(singer));
    }
}
