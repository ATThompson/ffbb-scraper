package fr.athompson.cron.spi;

import fr.athompson.domain.entities.Organisation;

public interface SPIGetOrganisation {

    Organisation getOrganisation(String idOrganisation);
}
