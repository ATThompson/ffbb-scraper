package fr.athompson.database.repositories;

import fr.athompson.database.entities.ClassementDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassementRepository extends CrudRepository<ClassementDB, Long> {
}
