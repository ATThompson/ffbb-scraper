package fr.athompson.database.repositories;

import fr.athompson.database.entities.RencontreDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                or renc.equipeVisiteur.organisationIdHtml = :idOrganisation)
            and renc.date between :dernierVendredi and :prochainJeudi
            and ( renc.scoreDomicile <> 0 or renc.scoreVisiteur <> 0 )
            order by renc.date asc
            limit 1""")
    Optional<RencontreDB> findDernierResultat(String idOrganisation, String idChampionnat, String idDivision, String idPoule, LocalDateTime dernierVendredi, LocalDateTime prochainJeudi);


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
                or renc.equipeVisiteur.organisationIdHtml = :idOrganisation)
            and renc.date < :prochainDimanche
            and ( renc.scoreDomicile = 0 and renc.scoreVisiteur = 0 )
            order by renc.date asc
            limit 1""")
    Optional<RencontreDB> findProchainMatch(String idOrganisation, String idChampionnat, String idDivision, String idPoule, LocalDateTime prochainDimanche);


}
