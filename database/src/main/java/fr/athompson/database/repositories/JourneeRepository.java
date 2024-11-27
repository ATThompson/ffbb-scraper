package fr.athompson.database.repositories;

import fr.athompson.database.entities.JourneeDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JourneeRepository extends CrudRepository<JourneeDB, Long> {

    Optional<List<JourneeDB>> findAllByCompetition_ChampionnatIdHtmlAndCompetition_DivisionIdHtmlAndCompetition_PouleIdHtml(String championnatIdHtml, String divisionIdHtml, String pouleIdHtml);

}
