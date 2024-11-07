package fr.athompson.scrap.services;

import fr.athompson.cron.entities.OrganisationScrap;
import fr.athompson.cron.spi.SPIGetOrganisation;
import fr.athompson.scrap.scrapers.organisation.OrganisationScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOrganisation implements SPIGetOrganisation {

    OrganisationScraper organisationScraper;

    @Override
    public OrganisationScrap getOrganisation(String idOrganisation) {
        return organisationScraper.getData(idOrganisation);
    }
}
