package fr.athompson.ffbbscraper.scrapers.classement;

import fr.athompson.ffbbscraper.entities.classement.Classement;

public interface APIClassementScraper {

    Classement scrap(String paramClassement);
}
