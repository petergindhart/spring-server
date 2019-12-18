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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_topic", joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<EmployeeEntity> enthusiasts;
}
