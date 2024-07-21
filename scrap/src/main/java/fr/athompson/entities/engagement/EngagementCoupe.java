package fr.athompson.entities.engagement;

import fr.athompson.enums.SexeCompetitionType;
import fr.athompson.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementCoupe extends Engagement {

    public EngagementCoupe(Map<SexeCompetitionType, List<Competition>> coupesEngagees) {
        super(coupesEngagees);
    }

    public EngagementCoupe() {
    }
}
