package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.CompetitionScrap;

import java.util.List;
import java.util.Map;

public class EngagementScrapPlateau extends EngagementScrap {

    public EngagementScrapPlateau(Map<SexeCompetitionType, List<CompetitionScrap>> plateauxEngages) {
        super(plateauxEngages);
    }

    public EngagementScrapPlateau() {
    }
}
