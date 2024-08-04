package fr.athompson.cron.spi;

import fr.athompson.domain.entities.Competition;

public interface SPIGetCompetition {

    Competition execute(String idOrganisation, String idDivision, String idPoule);
}
