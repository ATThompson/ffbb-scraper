package fr.athompson.scrap.services;

import fr.athompson.cron.spi.SPIGetCompetition;
import fr.athompson.domain.entities.Competition;
import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.mappers.CompetitionMapper;
import fr.athompson.scrap.scrapers.competition.CompetitionScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetCompetition implements SPIGetCompetition {
    CompetitionScraper competitionScraper;
    CompetitionMapper competitionMapper;

    public Competition execute(String idOrganisation, String idDivision, String idPoule){
        CompetitionScrap competitionScrap = competitionScraper.getData(idOrganisation, idDivision, idPoule);
        return competitionMapper.toDomain(competitionScrap);
    }

}
