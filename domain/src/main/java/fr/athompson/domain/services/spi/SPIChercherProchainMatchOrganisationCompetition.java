package fr.athompson.domain.services.spi;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.Rencontre;

import java.util.Optional;

public interface SPIChercherProchainMatchOrganisationCompetition {
    Optional<Rencontre> chercher(String idOrganisation, Competition competition);
}
