package fr.athompson.scrap.services;

import fr.athompson.cron.spi.SPIGetOrganisation;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.scrap.mappers.OrganisationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOrganisation implements SPIGetOrganisation {

    OrganisationMapper organisationMapper;
    @Override
    public Organisation getOrganisation(String idOrganisation) {
        System.out.println(idOrganisation);
        return organisationMapper.getDataAndMap(idOrganisation);
    }
}
