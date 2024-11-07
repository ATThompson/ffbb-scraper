package fr.athompson.cron.entities.engagement;

import fr.athompson.cron.entities.CompetitionScrap;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EngagementScrapCoupe extends EngagementScrap {
    public EngagementScrapCoupe(CompetitionScrap competitionEngagee) {
        super(competitionEngagee);
    }
}
