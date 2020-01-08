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

    @ManyToMany
    @JoinTable(name = "employee_topic", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"))
    private Set<TopicEntity> interests;


    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeEntity other = (EmployeeEntity) obj;
        return id != null && id.equals(other.getId());
    }
}
