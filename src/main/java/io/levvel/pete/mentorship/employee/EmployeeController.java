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
    public ResponseEntity<Void> employeeIdChangeManagerPost(Integer employeeId, @NotNull @Valid Integer newManagerId) {
        Employee existing = employeeService.getEmployeeById(employeeId);
        if (existing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        existing.setManagerId(newManagerId);
        employeeService.update(employeeId, existing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> employeeIdDelete(Integer employeeId, @Valid Integer reassignId) {
        Employee existing = employeeService.getEmployeeById(employeeId);
        if (existing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        employeeService.delete(existing.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> employeeIdDirectReportsGet(Integer managerId) {
        Employee manager = employeeService.getEmployeeById(managerId);
        if (manager == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Employee> reports = employeeService.getEmployeesByManagerId(managerId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> employeeIdGet(Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> employeeIdInterestsDelete(Integer employeeId, @NotNull @Valid Integer topicId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<List<Topic>> employeeIdInterestsGet(Integer employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> employeeIdInterestsPost(Integer employeeId, @NotNull @Valid Integer topicId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<Pet>> employeeIdPetsGet(Integer employeeId) {

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
