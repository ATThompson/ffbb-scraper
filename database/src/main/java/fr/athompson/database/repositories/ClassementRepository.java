package fr.athompson.database.repositories;

import fr.athompson.database.entities.Classement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassementRepository extends CrudRepository<Classement, Long> {
}
