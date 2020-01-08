package io.levvel.pete.mentorship.pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {
    List<PetEntity> findByOwner_Id(Integer ownerId);
}
