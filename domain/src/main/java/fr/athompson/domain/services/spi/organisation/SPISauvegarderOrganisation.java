package fr.athompson.domain.services.spi.organisation;

import fr.athompson.domain.entities.Organisation;

public interface SPISauvegarderOrganisation {
    void sauvegarder(Organisation organisation);
}
