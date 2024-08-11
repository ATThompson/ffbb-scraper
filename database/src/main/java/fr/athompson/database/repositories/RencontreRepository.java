package fr.athompson.database.repositories;

import fr.athompson.database.entities.RencontreDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RencontreRepository extends CrudRepository<RencontreDB, Long> {
    List<RencontreDB> findByJournee_journeeIdHtmlAndJournee_Competition_id(Integer integer, Long id);
}
