package fr.athompson.database.repositories;

import fr.athompson.database.entities.Organisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends CrudRepository<Organisation, Long> {
}
