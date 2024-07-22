package fr.athompson.database.repositories;

import fr.athompson.database.entities.Engagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementRepository extends CrudRepository<Engagement, Long> {
}
