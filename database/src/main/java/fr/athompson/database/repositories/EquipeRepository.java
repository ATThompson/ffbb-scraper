package fr.athompson.database.repositories;

import fr.athompson.database.entities.EquipeDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends CrudRepository<EquipeDB, Long> {
}
