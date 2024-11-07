package fr.athompson.cron.spi;

import fr.athompson.cron.entities.CompetitionScrap;

public interface SPISaveCompetition {
    void execute(CompetitionScrap competition);
}
