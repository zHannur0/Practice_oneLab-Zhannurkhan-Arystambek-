package org.example;

import org.example.model.Singer;
import org.example.service.KafkaJmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaServiceTest {

    private KafkaJmsService kafkaJmsService;

    @Test
    @DisplayName("Test sending user to Kafka")
    public void testSendUser() {
        // Arrange
        String topic = "test-topic";
        Singer singer = Singer.builder().singerId(10L).singerName("Taylor").genre("Hip-hop").country("USA").build();

        // Act
        kafkaJmsService.sendUser(topic, singer);
//
//        // Assert
//        verify(kafkaProducer, times(1)).sendUser(eq(topic), eq(singer));
    }
}
