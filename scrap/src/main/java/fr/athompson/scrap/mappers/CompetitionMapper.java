package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Competition;
import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.scrapers.Scraper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionMapper extends Mapper<CompetitionScrap, Competition> {
    public CompetitionMapper(Scraper<CompetitionScrap> scraper) {
        super(scraper);
    }

    @Override
    Competition map(CompetitionScrap competitionScrap) {
        return null;
    }
}
