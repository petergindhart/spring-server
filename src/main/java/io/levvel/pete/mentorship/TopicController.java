package io.levvel.pete.mentorship;

import io.swagger.api.TopicApi;
import io.swagger.model.Employee;
import io.swagger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TopicController implements TopicApi {
    @Autowired
    TopicService topicService;

    @Override
    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> topicGet() {
        List<TopicEntity> topicEntities = topicService.getAllTopics();
        List<Topic> topics = ConvertToApiModel(topicEntities);

//        TopicEntity newTopic = new TopicEntity(){{
//            setName("newguy");
//        }};
//        topicService.saveOrUpdate(newTopic);

        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> topicIdDelete(Integer id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> topicIdEnthusiastsGet(Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Topic> topicIdGet(Integer id) {
        TopicEntity topic = topicService.getTopicById(id);
        if (topic == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ConvertToApiModel(topic), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> topicIdPut(Integer id, @Valid Topic topic) {
        TopicEntity topicEntity = topicService.getTopicById(id);
        if (topicEntity == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        topicEntity.setName(topic.getName());
        topicService.saveOrUpdate(topicEntity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    @PostMapping("/topic")
    public ResponseEntity<Integer> topicPost(@Valid @RequestBody Topic topic) {
        TopicEntity entity = new TopicEntity(){{
            setName(topic.getName());
        }};
        topicService.saveOrUpdate(entity);
        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    public Topic ConvertToApiModel(TopicEntity entity) {
        Topic topic = new Topic(){{
            setId(entity.getId());
            setName(entity.getName());
        }};
        return topic;
    }

    public List<Topic> ConvertToApiModel(List<TopicEntity> entities) {
        return entities.stream().map(this::ConvertToApiModel).collect(Collectors.toList());
    }
}
