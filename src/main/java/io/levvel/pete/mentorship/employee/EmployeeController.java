package io.levvel.pete.mentorship.employee;

import io.swagger.api.EmployeeApi;
import io.swagger.model.Employee;
import io.swagger.model.Pet;
import io.swagger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class EmployeeController implements EmployeeApi {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> employeeGet() {
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> employeeIdChangeManagerPost(Integer integer, @NotNull @Valid Integer integer1) {
        return null;
    }

    @Override
    public ResponseEntity<Void> employeeIdDelete(Integer integer, @Valid Integer integer1) {
        return null;
    }

    @Override
    public ResponseEntity<List<Employee>> employeeIdDirectReportsGet(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<Employee> employeeIdGet(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<Void> employeeIdInterestsDelete(Integer integer, @NotNull @Valid Integer integer1) {
        return null;
    }

    @Override
    public ResponseEntity<List<Topic>> employeeIdInterestsGet(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<Void> employeeIdInterestsPost(Integer integer, @NotNull @Valid Integer integer1) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> employeeIdPetsGet(Integer integer) {
        return null;
    }
}
