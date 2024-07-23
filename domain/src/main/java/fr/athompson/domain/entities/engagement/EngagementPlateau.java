package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.SexeCompetitionType;

import java.util.List;
import java.util.Map;

public class EngagementPlateau extends Engagement {

    public EngagementPlateau(Map<SexeCompetitionType, List<Competition>> plateauxEngages) {
        super(plateauxEngages);
    }

    public EngagementPlateau() {
    }
}
