package fr.athompson.cron.spi;

import fr.athompson.cron.entities.OrganisationScrap;

public interface SPIGetOrganisation {

    OrganisationScrap getOrganisation(String idOrganisation);
}
