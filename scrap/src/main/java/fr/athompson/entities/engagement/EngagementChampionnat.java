package fr.athompson.entities.engagement;

import fr.athompson.enums.SexeCompetitionType;
import fr.athompson.entities.Competition;

import java.util.List;
import java.util.Map;

public class EngagementChampionnat extends Engagement {

    public EngagementChampionnat(Map<SexeCompetitionType, List<Competition>> championnatsEngages) {
        super(championnatsEngages);
    }

    public EngagementChampionnat() {
        super();
    }
}
