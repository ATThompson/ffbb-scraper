package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.entities.Competition;

public interface APICompetitionScraper {
    Competition scrap(String idOrganisation, String idDivision, String idPoule);

}
