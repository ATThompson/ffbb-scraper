package fr.athompson.ffbbscraper.scrapers.organisation;

import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.scrapers.engagement.APIEngagementScraper;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.Organisation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganisationScraper extends Scraper implements APIOrganisationScraper{

    APIEngagementScraper engagementScraper;

    public OrganisationScraper(APIEngagementScraper engagementScraper) {
        this.engagementScraper = engagementScraper;
    }

    @Override
    public Organisation scrap(String idOrganisation){
        List<Engagement> engagements = engagementScraper.scrap(idOrganisation);

        return new Organisation(engagements);
    }

}