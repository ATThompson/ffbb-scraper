package fr.athompson.database.repositories;

import fr.athompson.database.entities.Journee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneeRepository extends CrudRepository<Journee, Long> {
}
