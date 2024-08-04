package fr.athompson.cron.spi;

import fr.athompson.domain.entities.Organisation;

public interface SPISaveOrganisation {
    void saveOrganisation(Organisation organisation);
}
