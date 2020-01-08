package io.levvel.pete.mentorship.pet;

import io.levvel.pete.mentorship.employee.EmployeeService;
import io.swagger.api.PetApi;
import io.swagger.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PetController implements PetApi {
    PetService petService;
    EmployeeService employeeService;
    @Autowired
    public PetController(PetService petService, EmployeeService employeeService) {
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Pet>> petGet() {
        List<Pet> pets = petService.getAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> petIdDelete(Integer petId) {
        Pet existing = petService.getPetById(petId);
        if (existing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        petService.delete(existing.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pet> petIdGet(Integer petId) {
        Pet existing = petService.getPetById(petId);
        if (existing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(existing, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> petIdPut(Integer id, @Valid Pet pet) {
        if (petService.getPetById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        petService.update(id, pet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> petPost(@Valid Pet pet) {
        if (employeeService.getEmployeeById(pet.getOwner()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Integer id = petService.save(pet);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
