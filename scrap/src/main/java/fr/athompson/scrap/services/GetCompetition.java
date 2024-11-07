package fr.athompson.scrap.services;

import fr.athompson.cron.spi.SPIGetCompetition;
import fr.athompson.cron.entities.CompetitionScrap;
import fr.athompson.scrap.scrapers.competition.CompetitionScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetCompetition implements SPIGetCompetition {
    CompetitionScraper competitionScraper;

    public CompetitionScrap execute(String idOrganisation, String idDivision, String idPoule, String libellePoule){
        return competitionScraper.getData(idOrganisation, idDivision, idPoule, libellePoule);
    }

}
