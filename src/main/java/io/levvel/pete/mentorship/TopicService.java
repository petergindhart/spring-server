package io.levvel.pete.mentorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    public List<TopicEntity> getAllTopics() {
        List<TopicEntity> topics = new ArrayList<>();
        topicRepository.findAll().forEach(emp -> topics.add(emp));
        return topics;
    }

    public TopicEntity getTopicById(int id) {
        return topicRepository.getOne(id);
    }

    public void saveOrUpdate(TopicEntity topic) {
        topicRepository.save(topic);
    }

    public void delete(int id) {
        topicRepository.deleteById(id);
    }
}
