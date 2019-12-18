package io.levvel.pete.mentorship.employee;

import io.swagger.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.DateTimeUtils;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        List<EmployeeEntity> entities = repository.findAll();
        return ConvertToApiModel(entities);
    }

    public Employee ConvertToApiModel(EmployeeEntity entity) {
        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setName(entity.getName());
        employee.setHireDate(DateTimeUtils.toLocalDate(new java.sql.Date(entity.getHireDate().getTime())));
        employee.setAgeAtHire(entity.getAgeAtHire());
        employee.setIsEskimo(entity.isEskimo());

        // TODO: /employee/{id}/manager?
        EmployeeEntity manager = entity.getManager();
        if (manager != null) {
            employee.setManagerId(manager.getId());
        }

        Calendar calToday = Calendar.getInstance();
        Calendar calHire = Calendar.getInstance();
        calHire.setTime(entity.getHireDate());

        System.out.println(calToday.get(Calendar.YEAR));
        System.out.println(calHire.get(Calendar.YEAR));
        Integer addYears = calToday.get(Calendar.YEAR) - calHire.get(Calendar.YEAR);
        employee.setApproximateAge(entity.getAgeAtHire() + addYears);

        return employee;
    }

    public List<Employee> ConvertToApiModel(List<EmployeeEntity> entities) {
        return entities.stream().map(this::ConvertToApiModel).collect(Collectors.toList());
    }
}