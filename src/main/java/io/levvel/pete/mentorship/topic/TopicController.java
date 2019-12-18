package io.levvel.pete.mentorship.topic;

import io.swagger.api.TopicApi;
import io.swagger.model.Employee;
import io.swagger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TopicController implements TopicApi {
    TopicService topicService;

    @Autowired
    public TopicController (TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<List<Topic>> topicGet() {
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
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
        Topic topic = topicService.getTopicById(id);
        if (topic == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> topicIdPut(Integer id, @Valid Topic topic) {
        if (topicService.getTopicById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        topicService.update(id, topic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> topicPost(@Valid @RequestBody Topic topic) {
        Integer id = topicService.save(topic);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
