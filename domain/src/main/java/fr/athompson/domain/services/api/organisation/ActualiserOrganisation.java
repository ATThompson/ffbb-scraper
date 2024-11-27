package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import fr.athompson.domain.services.spi.competition.SPISauvegarderCompetition;
import fr.athompson.domain.services.spi.competition.SPIScrapCompetition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ActualiserOrganisation {

    SPIChercherCompetitionsEngagees getAllCompetitions;
    SPIScrapCompetition scrapCompetition;
    SPISauvegarderCompetition saveCompetition;
    public void execute(String idOrganisation) {
        var competitions = getAllCompetitions.chercher(idOrganisation);
        for(Competition competition : competitions){
            Competition competitionScrapped = scrapCompetition.scrap(competition.idChampionnat(),
                                                                     competition.idDivision(),
                                                                     competition.idPoule(),
                                                                     competition.poule());
            saveCompetition.sauvegarder(competitionScrapped);
        }

    }

}
