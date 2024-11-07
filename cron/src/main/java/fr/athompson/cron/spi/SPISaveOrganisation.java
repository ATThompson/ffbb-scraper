package fr.athompson.cron.spi;

import fr.athompson.cron.entities.OrganisationScrap;

public interface SPISaveOrganisation {
    void saveOrganisation(OrganisationScrap organisation);
}
