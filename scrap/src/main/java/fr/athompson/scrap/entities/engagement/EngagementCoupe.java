package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementCoupe extends Engagement {

    public EngagementCoupe(Map<SexeCompetitionType, List<Competition>> coupesEngagees) {
        super(coupesEngagees);
    }

    public EngagementCoupe() {
    }
}
