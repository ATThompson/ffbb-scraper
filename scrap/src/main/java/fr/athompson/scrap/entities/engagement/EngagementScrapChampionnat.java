package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.CompetitionScrap;

import java.util.List;
import java.util.Map;

public class EngagementScrapChampionnat extends EngagementScrap {

    public EngagementScrapChampionnat(Map<SexeCompetitionType, List<CompetitionScrap>> championnatsEngages) {
        super(championnatsEngages);
    }

    public EngagementScrapChampionnat() {
        super();
    }
}
