package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.scrapers.api.PublicMethodScrap;

import java.util.List;

/**
 * Params requis pour getData idOrganisation
 */
public interface APIEngagementScraper extends PublicMethodScrap<List<Engagement>> {
}
