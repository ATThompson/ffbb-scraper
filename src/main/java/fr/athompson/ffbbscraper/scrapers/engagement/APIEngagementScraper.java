package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.entities.engagement.Engagement;

import java.util.List;

public interface APIEngagementScraper {
    List<Engagement> scrap(String idOrganisation);
}
