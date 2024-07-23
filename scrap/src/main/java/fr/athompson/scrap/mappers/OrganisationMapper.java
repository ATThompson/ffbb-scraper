package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.scrap.entities.OrganisationScrap;
import fr.athompson.scrap.scrapers.Scraper;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper extends Mapper<OrganisationScrap, Organisation> {

    public OrganisationMapper(Scraper<OrganisationScrap> scraper) {
        super(scraper);
    }

    @Override
    Organisation map(OrganisationScrap organisationScrap) {
        return new Organisation(null,null);
    }
}
