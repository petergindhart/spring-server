package io.levvel.pete.mentorship.employee;

import io.levvel.pete.mentorship.topic.TopicEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    private Integer ageAtHire;

    private boolean isEskimo;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity manager;

    @ManyToMany(mappedBy = "enthusiasts")
    private Set<TopicEntity> interests;
}
