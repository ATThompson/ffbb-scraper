package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.SexeCompetitionType;

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
