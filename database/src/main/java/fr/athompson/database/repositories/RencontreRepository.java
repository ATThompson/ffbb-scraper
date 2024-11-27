package fr.athompson.database.repositories;

import fr.athompson.database.entities.RencontreDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RencontreRepository extends CrudRepository<RencontreDB, Long> {
    List<RencontreDB> findByJournee_journeeIdHtmlAndJournee_Competition_id(Integer integer, Long id);

    @Query("""
            select renc from CompetitionDB compet
            join JourneeDB journ
            on compet.id = journ.competition.id
            join RencontreDB renc
            on journ.id = renc.journee.id
            where compet.championnatIdHtml = :idChampionnat
            and compet.divisionIdHtml = :idDivision
            and compet.pouleIdHtml = :idPoule
            and (renc.equipeDomicile.organisationIdHtml = :idOrganisation
                or renc.equipeVisiteur.organisationIdHtml = :idOrganisation)""")
    List<RencontreDB> findAllByCompetitionAndEquipe(String idOrganisation, String idChampionnat, String idDivision, String idPoule);

}
