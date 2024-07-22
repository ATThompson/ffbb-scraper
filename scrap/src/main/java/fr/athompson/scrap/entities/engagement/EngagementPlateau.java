package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementPlateau extends Engagement {

    public EngagementPlateau(Map<SexeCompetitionType, List<Competition>> plateauxEngages) {
        super(plateauxEngages);
    }

    public EngagementPlateau() {
    }
}
