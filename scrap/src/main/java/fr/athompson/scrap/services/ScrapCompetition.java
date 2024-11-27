package fr.athompson.scrap.services;


import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.spi.competition.SPIScrapCompetition;
import fr.athompson.scrap.mappers.CompetitionMapper;
import fr.athompson.scrap.scrapers.competition.CompetitionScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScrapCompetition implements SPIScrapCompetition {
    CompetitionScraper competitionScraper;
    CompetitionMapper competitionMapper;
    public Competition scrap(String idOrganisation, String idDivision, String idPoule, String libellePoule){
        return competitionMapper.toDomain(competitionScraper.getData(idOrganisation, idDivision, idPoule, libellePoule));
    }

}
