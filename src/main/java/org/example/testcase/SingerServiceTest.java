package org.example.testcase;

import org.example.model.Singer;
import org.example.repository.SingerRepository;
import org.example.service.SingerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SingerServiceTest {

    @Autowired
    private SingerService singerService;

    @Test
    @DisplayName("Test getting all singers")
    public void testGettingAllSingers() {
        List<Singer> singers = singerService.getAllSingers();
        assertNotNull(singers, "Getting did not work");
    }

    @Test
    @DisplayName("Test saving")
    public void testSavingAllSingers() {
        Singer singer = new Singer(10L,"asdasd", "asd", "asd");
        Singer singer1 = new Singer(11L,"asdasd", "asd", "asd");

        List<Singer> singers = singerService.getAllSingers();
        List<Singer> testSingers = new ArrayList<>();
        testSingers.add(singer);
        testSingers.add(singer1);

        singerService.saveAllSingers(testSingers);

        List<Singer> singersTestSize = singerService.getAllSingers();

        assertTrue(singers.size() < singersTestSize.size(), "It did not working");
    }
}
