package fr.athompson.database.services.api.competition;


import fr.athompson.database.entities.*;
import fr.athompson.database.mappers.ClassementMapperDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.ClassementRepository;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.database.repositories.RencontreRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.entities.classement.RowClassement;
import fr.athompson.domain.services.spi.competition.SPISauvegarderCompetition;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class SauvegarderCompetition implements SPISauvegarderCompetition {


    CompetitionRepository competitionRepository;

    ClassementMapperDB classementMapperDB;

    ClassementRepository classementRepository;

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;
    @Transactional
    public void sauvegarder(Competition competition) {
        Optional<CompetitionDB> optionalCompetitionPresenteEnBase = competitionRepository.findByChampionnatIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                competition.idChampionnat(),
                competition.idDivision(),
                competition.idPoule()
        );
        if (optionalCompetitionPresenteEnBase.isPresent()) {
            CompetitionDB competitionDB = optionalCompetitionPresenteEnBase.get();
            var classement = competitionDB.getClassements();

            if(classement.isEmpty()) {
                // Cas où la compétition avait déjà été crée et enregistrer en base mais le classement n'existait pas
                // Donc on le crée maitenant
                for (RowClassement rowClassement : competition.classement().rowsClassement()) {

                    String idOrganisationEquipe = rowClassement.equipe().idOrganisation();
                    String nomEquipe = rowClassement.equipe().nom();

                    EquipeDB equipeDB = competitionDB.getJournees().stream().map(JourneeDB::getRencontres).flatMap(Collection::stream).map(RencontreDB::getEquipeDomicile).filter(
                            e -> idOrganisationEquipe.equals(e.getOrganisationIdHtml()) && nomEquipe.equals(e.getNom())
                    ).findFirst().orElseThrow(() -> new RuntimeException("Pas d'équipe trouvée pour la création du classement"));

                    var rowClassementToCreate = classementMapperDB.toDatabase(rowClassement);
                    rowClassementToCreate.setEquipe(equipeDB);
                    rowClassementToCreate.setCompetition(competitionDB);
                    classementRepository.save(rowClassementToCreate);
                }
            }else{
                for (RowClassement rowClassement : competition.classement().rowsClassement()) {
                    ClassementDB ligneClassementDejaPresenteEnBase = classement.stream()
                            .filter(equipeDB ->
                                    equipeDB.getEquipe().getNom().equals(rowClassement.equipe().nom())
                                            && equipeDB.getEquipe().getOrganisationIdHtml().equals(rowClassement.equipe().idOrganisation())).findFirst().get();

                    var classementDBToSave = classementMapperDB.toDatabase(rowClassement);

                    classementDBToSave.setEquipe(ligneClassementDejaPresenteEnBase.getEquipe());
                    classementDBToSave.setCompetition(ligneClassementDejaPresenteEnBase.getCompetition());
                    classementDBToSave.setId(ligneClassementDejaPresenteEnBase.getId());

                    classementRepository.save(classementDBToSave);
                }
            }


            for (Journee journee : competition.journees()) {
                List<RencontreDB> rencontresDB = rencontreRepository.findByJournee_journeeIdHtmlAndJournee_Competition_id(journee.idJournee(), competitionDB.getId());
                for (RencontreDB rencontreDB : rencontresDB) {
                    Rencontre rencontreScrap = journee.rencontres().stream().filter(j -> j.numeroRencontre().equals(rencontreDB.getRencontreIdHtml())).findFirst().orElse(null);
                    var rencontreDBToSave = rencontreMapperDB.toDatabase(rencontreScrap);
                    if(null == rencontreDBToSave) {
                        log.warn("Erreur lors de la mise à jour de la rencontre : {} pour le championnat {}", rencontreDB.getRencontreIdHtml(), competition.nom());
                    }else{
                        rencontreDBToSave.setJournee(rencontreDB.getJournee());
                        rencontreDBToSave.setId(rencontreDB.getId());
                        rencontreDBToSave.setEquipeVisiteur(rencontreDB.getEquipeVisiteur());
                        rencontreDBToSave.setEquipeDomicile(rencontreDB.getEquipeDomicile());
                        rencontreRepository.save(rencontreDBToSave);
                    }
                }
            }
        }
    }

}
