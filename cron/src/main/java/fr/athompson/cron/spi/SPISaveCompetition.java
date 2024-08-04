package fr.athompson.cron.spi;

import fr.athompson.domain.entities.Competition;

public interface SPISaveCompetition {
    void execute(Competition competition);
}
