package io.levvel.pete.mentorship.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    TopicRepository repository;

    @Autowired
    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<TopicEntity> getAllTopics() {
        List<TopicEntity> topics = new ArrayList<>();
        repository.findAll().forEach(emp -> topics.add(emp));
        return topics;
    }

    public TopicEntity getTopicById(int id) {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(TopicEntity topic) {
        repository.save(topic);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
