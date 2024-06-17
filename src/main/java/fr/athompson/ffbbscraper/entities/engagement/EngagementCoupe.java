package fr.athompson.ffbbscraper.entities.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementCoupe extends Engagement {

    public EngagementCoupe(Map<SexeCompetitionType, List<Competition>> coupesEngagees) {
        super(coupesEngagees);
    }

    public EngagementCoupe() {
    }
}
