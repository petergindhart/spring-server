package io.levvel.pete.mentorship.pet;

import io.levvel.pete.mentorship.employee.EmployeeEntity;
import io.levvel.pete.mentorship.employee.EmployeeRepository;
import io.swagger.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    PetRepository petRepo;
    EmployeeRepository employeeRepo;

    @Autowired
    public PetService(PetRepository petRepo, EmployeeRepository employeeRepo) {
        this.petRepo = petRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<Pet> getAll() {
        List<PetEntity> entities = petRepo.findAll();
        return ConvertToApiModel(entities);
    }

    public Pet getPetById(Integer petId) {
        Optional<PetEntity> entity = petRepo.findById(petId);
        return entity.isPresent() ? ConvertToApiModel(entity.get()) : null;
    }

    public List<Pet> getPetsByOwnerId(Integer ownerId) {
        return ConvertToApiModel(petRepo.findByOwner_Id(ownerId));
    }

    public Integer save(Pet pet) {
        PetEntity entity = new PetEntity();
        entity.setName(pet.getName());
        entity.setOwner(employeeRepo.getOne(pet.getOwner()));

        entity = petRepo.save(entity);
        return entity.getId();
    }

    public void update(Integer id, Pet pet) {
        PetEntity existing = petRepo.getOne(id);
        if (existing != null)        {
            existing.setName(pet.getName());

            EmployeeEntity owner = employeeRepo.getOne(pet.getOwner());
            if (owner != null) {
                existing.setOwner(owner);
            }

            petRepo.save(existing);
        }
    }

    public void delete(Integer petId) {
        petRepo.deleteById(petId);
    }

    public static Pet ConvertToApiModel(PetEntity entity) {
        Pet pet = new Pet();
        pet.setId(entity.getId());
        pet.setName(entity.getName());
        EmployeeEntity owner = entity.getOwner();
        if (owner != null) {
            pet.setOwner(owner.getId());
        }
        return pet;
    }

    public static List<Pet> ConvertToApiModel(List<PetEntity> entities) {
        return entities.stream().map(PetService::ConvertToApiModel).collect(Collectors.toList());
    }
}
