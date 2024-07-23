package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.scrapers.Scraper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EngagementMapper extends Mapper<List<EngagementScrap>, List<Engagement>> {

    public EngagementMapper(Scraper<List<EngagementScrap>> scraper) {
        super(scraper);
    }

    @Override
    List<Engagement> map(List<EngagementScrap> engagementScraps) {
        return List.of();
    }
}
