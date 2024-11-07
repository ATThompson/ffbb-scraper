package fr.athompson.cron.spi;

import fr.athompson.cron.entities.CompetitionScrap;

import java.util.List;

public interface SPIGetAllCompetitions {

    List<CompetitionScrap> execute();
}
