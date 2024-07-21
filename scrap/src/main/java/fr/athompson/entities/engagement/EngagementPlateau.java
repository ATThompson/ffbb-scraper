package fr.athompson.entities.engagement;

import fr.athompson.enums.SexeCompetitionType;
import fr.athompson.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementPlateau extends Engagement {

    public EngagementPlateau(Map<SexeCompetitionType, List<Competition>> plateauxEngages) {
        super(plateauxEngages);
    }

    public EngagementPlateau() {
    }
}
