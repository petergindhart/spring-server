package io.levvel.pete.mentorship.topic;

import io.swagger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {
    TopicRepository repository;

    @Autowired
    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAllTopics() {
        return ConvertToApiModel(repository.findAll());
    }

    public Topic getTopicById(int id) {
        Optional<TopicEntity> entity = repository.findById(id);
        return entity.isPresent() ? ConvertToApiModel(entity.get()) : null;
    }

    public Integer save(Topic topic) {
        TopicEntity entity = new TopicEntity();
        entity.setName(topic.getName());
        entity = repository.save(entity);
        return entity.getId();
    }

    public void update(Integer id, Topic topic) {
        TopicEntity existing = repository.getOne(id);
        if (existing != null)        {
            existing.setName(topic.getName());
            repository.save(existing);
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public static Topic ConvertToApiModel(TopicEntity entity) {
        Topic topic = new Topic();
        topic.setId(entity.getId());
        topic.setName(entity.getName());
        return topic;
    }

    public static List<Topic> ConvertToApiModel(List<TopicEntity> entities) {
        return entities.stream().map(TopicService::ConvertToApiModel).collect(Collectors.toList());
    }
}
