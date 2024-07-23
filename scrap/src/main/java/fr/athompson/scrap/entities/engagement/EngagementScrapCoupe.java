package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.CompetitionScrap;

import java.util.List;
import java.util.Map;

public class EngagementScrapCoupe extends EngagementScrap {

    public EngagementScrapCoupe(Map<SexeCompetitionType, List<CompetitionScrap>> coupesEngagees) {
        super(coupesEngagees);
    }

    public EngagementScrapCoupe() {
    }
}
