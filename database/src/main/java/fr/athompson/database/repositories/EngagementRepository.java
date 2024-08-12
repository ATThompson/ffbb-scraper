package fr.athompson.database.repositories;

import fr.athompson.database.entities.EngagementDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngagementRepository extends CrudRepository<EngagementDB, Long> {

    List<EngagementDB> findByOrganisation_OrganisationIdHtml(String idOrganisation);
}
