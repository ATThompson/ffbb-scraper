package fr.athompson.ffbbscraper.scrapers.journee;

import fr.athompson.ffbbscraper.entities.Journee;

public interface APIJourneeScraper {
    Journee scrap(String paramJournee, int page);
}
