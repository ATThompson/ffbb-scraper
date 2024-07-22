package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.Competition;

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
