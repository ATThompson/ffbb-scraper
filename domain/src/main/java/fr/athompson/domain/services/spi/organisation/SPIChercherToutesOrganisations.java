package fr.athompson.domain.services.spi.organisation;

import fr.athompson.domain.entities.Organisation;

import java.util.List;

public interface SPIChercherToutesOrganisations {

    List<Organisation> chercher();
}
