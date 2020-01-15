package io.levvel.pete.mentorship.topic;

import io.swagger.model.Topic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TopicServiceTest {
    @Mock
    private TopicRepository topicRepo;

    private TopicService topicService;

    @BeforeEach
    void setUp() {
        topicService = new TopicService(topicRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllTopics() {
        TopicEntity a = new TopicEntity();
        a.setId(1);
        a.setName("alpha");
        TopicEntity b = new TopicEntity();
        b.setId(2);
        b.setName("bravo");
        TopicEntity c = new TopicEntity();
        c.setId(3);
        c.setName("charlie");
        List<TopicEntity> mockFindAll = Arrays.asList(a, b, c);
        when(topicRepo.findAll()).thenReturn(mockFindAll);

        List<Topic> expected = TopicService.ConvertToApiModel(mockFindAll);
        List<Topic> actual = topicService.getAllTopics();
        assertEquals(expected, actual);
    }

    @Test
    void getTopicById() {
        TopicEntity a = new TopicEntity();
        a.setId(1);
        a.setName("alpha");

        when(topicRepo.findById(1)).thenReturn(Optional.of(a));
        Topic expected = TopicService.ConvertToApiModel(a);
        Topic actual = topicService.getTopicById(1);
        assertEquals(expected, actual);
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getEnthusiasts() {
    }

    @Test
    void instanceConvertToApiModel() {
    }

    @Test
    void listConvertToApiModel() {
    }
}