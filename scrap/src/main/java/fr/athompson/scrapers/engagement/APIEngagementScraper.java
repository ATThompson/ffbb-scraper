package fr.athompson.scrapers.engagement;

import fr.athompson.entities.engagement.Engagement;
import fr.athompson.scrapers.api.PublicMethodScrap;

import java.util.List;

/**
 * Params requis pour getData idOrganisation
 */
public interface APIEngagementScraper extends PublicMethodScrap<List<Engagement>> {
}
