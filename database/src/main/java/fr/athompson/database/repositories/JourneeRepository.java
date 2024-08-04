package fr.athompson.database.repositories;

import fr.athompson.database.entities.JourneeDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneeRepository extends CrudRepository<JourneeDB, Long> {
}
