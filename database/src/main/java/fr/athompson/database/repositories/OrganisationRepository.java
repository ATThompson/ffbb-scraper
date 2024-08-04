package fr.athompson.database.repositories;

import fr.athompson.database.entities.OrganisationDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepository extends CrudRepository<OrganisationDB, Long> {
    List<OrganisationDB> findAll();

    Optional<OrganisationDB> findByNom(String nom);
}
