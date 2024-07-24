package fr.athompson.scrap.services;

import fr.athompson.cron.spi.SPIGetOrganisation;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.scrap.mappers.OrganisationMapper;
import fr.athompson.scrap.scrapers.organisation.OrganisationScraper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOrganisation implements SPIGetOrganisation {

    OrganisationMapper organisationMapper;

    OrganisationScraper organisationScraper;

    @Override
    public Organisation getOrganisation(String idOrganisation) {
        var organisationScrap = organisationScraper.getData(idOrganisation);
        return organisationMapper.toDomain(organisationScrap);
    }
}
