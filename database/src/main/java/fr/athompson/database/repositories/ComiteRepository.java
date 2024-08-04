package fr.athompson.database.repositories;

import fr.athompson.database.entities.ClassementDB;
import fr.athompson.database.entities.ComiteDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComiteRepository extends CrudRepository<ComiteDB, Long> {
    Optional<ComiteDB> findByComiteIdHtml(String comiteIdHtml);
}
