package io.levvel.pete.mentorship.topic;

import io.levvel.pete.mentorship.employee.EmployeeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "interests")
    private Set<EmployeeEntity> enthusiasts;
}
