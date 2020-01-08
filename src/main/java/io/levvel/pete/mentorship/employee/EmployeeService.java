package io.levvel.pete.mentorship.employee;

import io.levvel.pete.mentorship.topic.TopicEntity;
import io.levvel.pete.mentorship.topic.TopicRepository;
import io.levvel.pete.mentorship.topic.TopicService;
import io.swagger.model.Employee;
import io.swagger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepo;
    TopicRepository topicRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo, TopicRepository topicRepo) {
        this.employeeRepo = employeeRepo;
        this.topicRepo = topicRepo;
    }

    public List<Employee> getAll() {
        List<EmployeeEntity> entities = employeeRepo.findAll();
        return ConvertToApiModel(entities);
    }

    public Employee getEmployeeById(Integer employeeId) {
        Optional<EmployeeEntity> entity = employeeRepo.findById(employeeId);
        return entity.isPresent() ? ConvertToApiModel(entity.get()) : null;
    }

    public List<Employee> getEmployeesByManagerId(Integer managerId) {
        return ConvertToApiModel(employeeRepo.findByManager_Id(managerId));
    }

    public void update(Integer id, Employee employee) {
        EmployeeEntity existing = employeeRepo.getOne(id);
        EmployeeEntity manager = employeeRepo.getOne(employee.getManagerId());
        if (existing != null)        {
            existing.setName(employee.getName());
            existing.setManager(manager);
            employeeRepo.save(existing);
        }
    }

    public void delete(int id) {
        employeeRepo.deleteById(id);
    }

    public List<Topic> getInterests(Integer employeeId) {
        List<TopicEntity> topics = new ArrayList<>(employeeRepo.getOne(employeeId)
                .getInterests());
        return TopicService.ConvertToApiModel(topics);
    }

    public void removeInterest(Integer employeeId, Integer topicId) {
        EmployeeEntity employee = employeeRepo.getOne(employeeId);
        employee.getInterests().removeIf(i -> i.getId() == topicId);
        employeeRepo.save(employee);
    }

    public void addInterest(Integer employeeId, Integer topicId) {
        EmployeeEntity employee = employeeRepo.getOne(employeeId);
        TopicEntity topic = topicRepo.getOne(topicId);
        employee.getInterests().remove(topic);
        employee.getInterests().add(topic);
        employeeRepo.save(employee);
    }

    public static Employee ConvertToApiModel(EmployeeEntity entity) {
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

    public static List<Employee> ConvertToApiModel(List<EmployeeEntity> entities) {
        return entities.stream().map(EmployeeService::ConvertToApiModel).collect(Collectors.toList());
    }
}
