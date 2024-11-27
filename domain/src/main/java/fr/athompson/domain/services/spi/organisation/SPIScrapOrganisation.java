package fr.athompson.domain.services.spi.organisation;

import fr.athompson.domain.entities.Organisation;

public interface SPIScrapOrganisation {

    Organisation scrap(String idOrganisation);
}
