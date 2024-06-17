package fr.athompson.ffbbscraper.scrapers.organisation;

import fr.athompson.ffbbscraper.entities.Organisation;

public interface APIOrganisationScraper {
    Organisation scrap(String idOrganisation);
}
