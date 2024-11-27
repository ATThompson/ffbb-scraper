package fr.athompson.domain.services.spi.competition;

import fr.athompson.domain.entities.Competition;

public interface SPIScrapCompetition {
    Competition scrap(String idOrganisation, String idDivision, String idPoule, String libellePoule);
}
