package fr.athompson.domain.services.spi.competition;

import fr.athompson.domain.entities.Competition;

import java.util.List;

public interface SPIChercherCompetitionsEngagees {

    List<Competition> chercher(String idOrganisation);
}
