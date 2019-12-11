package io.levvel.pete.mentorship;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class TopicEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
