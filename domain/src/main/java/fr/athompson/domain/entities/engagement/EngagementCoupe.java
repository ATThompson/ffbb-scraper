package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.SexeCompetitionType;

import java.util.List;
import java.util.Map;

public class EngagementCoupe extends Engagement {

    public EngagementCoupe(Map<SexeCompetitionType, List<Competition>> coupesEngagees) {
        super(coupesEngagees);
    }

    public EngagementCoupe() {
    }
}
