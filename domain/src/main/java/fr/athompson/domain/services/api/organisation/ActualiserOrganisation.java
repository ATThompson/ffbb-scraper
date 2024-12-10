package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import fr.athompson.domain.services.spi.competition.SPISauvegarderCompetition;
import fr.athompson.domain.services.spi.competition.SPIScrapCompetition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class ActualiserOrganisation {

    SPIChercherCompetitionsEngagees getAllCompetitions;
    SPIScrapCompetition scrapCompetition;
    SPISauvegarderCompetition saveCompetition;
    public void execute(String idOrganisation) {
        var competitions = getAllCompetitions.chercher(idOrganisation);
        for(Competition competition : competitions){
            log.info("Début de l'actualisation de la compétition : {}", competition.nom());
            Competition competitionScrapped = scrapCompetition.scrap(competition.idChampionnat(),
                                                                     competition.idDivision(),
                                                                     competition.idPoule(),
                                                                     competition.poule());
            saveCompetition.sauvegarder(competitionScrapped);
            log.info("Fin de l'actualisation de la compétition : {}", competition.nom());
        }

    }

}
