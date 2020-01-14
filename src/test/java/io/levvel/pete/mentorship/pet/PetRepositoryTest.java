package io.levvel.pete.mentorship.pet;

import io.levvel.pete.mentorship.employee.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PetRepositoryTest {

    @Autowired private PetRepository petRepo;
    @Autowired private TestEntityManager entityManager;

    @Test
    void findByOwner_Id() {
        EmployeeEntity emp = new EmployeeEntity();
        Integer empId = entityManager.persistAndGetId(emp, Integer.class);
        assertThat(empId).isNotNull();

        PetEntity pet = new PetEntity();
        pet.setOwner(emp);
        Integer petId = entityManager.persistAndGetId(pet, Integer.class);
        assertThat(petId).isNotNull();

        List<PetEntity> found = petRepo.findByOwner_Id(empId);
        assertThat(found.size()).isEqualTo(1);
        PetEntity found_0 = found.get(0);
        assertThat(found_0.getId()).isEqualTo(petId);
        assertThat(found_0.getOwner()).isNotNull();
        assertThat(found_0.getOwner().getId()).isEqualTo(empId);
    }
}