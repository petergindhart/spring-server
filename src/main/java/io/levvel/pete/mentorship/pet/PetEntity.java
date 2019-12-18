package io.levvel.pete.mentorship.pet;

import io.levvel.pete.mentorship.employee.EmployeeEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn
    private EmployeeEntity owner;
}
