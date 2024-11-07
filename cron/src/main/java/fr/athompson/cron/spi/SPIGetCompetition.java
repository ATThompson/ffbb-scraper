package fr.athompson.cron.spi;

import fr.athompson.cron.entities.CompetitionScrap;

public interface SPIGetCompetition {

    CompetitionScrap execute(String idOrganisation, String idDivision, String idPoule, String libellePoule);
}
