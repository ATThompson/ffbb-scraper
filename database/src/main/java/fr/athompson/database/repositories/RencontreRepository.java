package fr.athompson.database.repositories;

import fr.athompson.database.entities.Rencontre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RencontreRepository extends CrudRepository<Rencontre, Long> {
}
