package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Journee;
import fr.athompson.scrap.entities.JourneeScrap;
import fr.athompson.scrap.scrapers.Scraper;
import org.springframework.stereotype.Component;

@Component
public class JourneeMapper extends Mapper<JourneeScrap, Journee> {

    public JourneeMapper(Scraper<JourneeScrap> scraper) {
        super(scraper);
    }

    @Override
    Journee map(JourneeScrap journeeScrap) {
        return null;
    }
}
