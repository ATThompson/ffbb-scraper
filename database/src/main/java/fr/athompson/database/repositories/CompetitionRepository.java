package fr.athompson.database.repositories;

import fr.athompson.database.entities.CompetitionDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends CrudRepository<CompetitionDB, Long> {

    Optional<CompetitionDB> findByNiveauAndDivisionAndCategorieAndTypeAndSexeAndNombrePoulesAndAnneeAndComite_Id(
            String niveau,
            Integer division,
            String categorie,
            String Type,
            String sexe,
            Integer nombrePoules,
            Integer annee,
            Long comiteId
    );

    Optional<CompetitionDB> findByOrganisationIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
            String organisationIdHtml,
            String divisionIdHtml,
            String pouleIdHtml
    );

    List<CompetitionDB> findAll();
}
