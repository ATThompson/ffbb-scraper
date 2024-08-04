package fr.athompson.cron.spi;

import fr.athompson.domain.entities.Competition;

import java.util.List;

public interface SPIGetAllCompetitions {

    List<Competition> execute();
}
