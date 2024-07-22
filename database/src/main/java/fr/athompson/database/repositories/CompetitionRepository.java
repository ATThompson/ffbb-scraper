package fr.athompson.database.repositories;

import fr.athompson.database.entities.Competition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> {
}
