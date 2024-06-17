package fr.athompson.ffbbscraper.entities.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementPlateau extends Engagement {

    public EngagementPlateau(Map<SexeCompetitionType, List<Competition>> plateauxEngages) {
        super(plateauxEngages);
    }

    public EngagementPlateau() {
    }
}
