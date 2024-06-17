package fr.athompson.ffbbscraper.entities.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.entities.Competition;

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
