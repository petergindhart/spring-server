package io.levvel.pete.mentorship.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private TestEntityManager entityManager;

    @Test
    void findByManager_Id() {
        EmployeeEntity boss = new EmployeeEntity();
        Integer bossId = entityManager.persistAndGetId(boss, Integer.class);
        assertThat(bossId).isNotNull();

        EmployeeEntity peon = new EmployeeEntity();
        peon.setManager(boss);
        Integer peonId = entityManager.persistAndGetId(peon, Integer.class);
        assertThat(peonId).isNotNull();

        List<EmployeeEntity> found = employeeRepo.findByManager_Id(bossId);
        assertThat(found.size()).isEqualTo(1);
        EmployeeEntity found_0 = found.get(0);
        assertThat(found_0.getId()).isEqualTo(peonId);
        assertThat(found_0.getManager()).isNotNull();
        assertThat(found_0.getManager().getId()).isEqualTo(bossId);

    }
}