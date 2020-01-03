package io.levvel.pete.mentorship.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findByManager(EmployeeEntity manager);
}