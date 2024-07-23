package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.classement.Classement;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.scrapers.Scraper;
import org.springframework.stereotype.Component;

@Component
public class ClassementMapper extends Mapper<ClassementScrap, Classement> {

    public ClassementMapper(Scraper<ClassementScrap> scraper) {
        super(scraper);
    }

    @Override
    Classement map(ClassementScrap classementScrap) {
        return null;
    }
}
