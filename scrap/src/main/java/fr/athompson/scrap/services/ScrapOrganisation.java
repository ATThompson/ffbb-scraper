package fr.athompson.scrap.services;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.services.spi.organisation.SPIScrapOrganisation;
import fr.athompson.scrap.mappers.OrganisationMapper;
import fr.athompson.scrap.scrapers.organisation.OrganisationScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScrapOrganisation implements SPIScrapOrganisation {

    OrganisationScraper organisationScraper;

    OrganisationMapper organisationMapper;

    public Organisation scrap(String idOrganisation) {
        return organisationMapper.toDomain(organisationScraper.getData(idOrganisation));
    }
}
