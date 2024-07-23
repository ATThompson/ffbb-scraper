package fr.athompson.scrap.entities.engagement.factory;

import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.scrap.entities.engagement.EngagementScrapCoupe;
import fr.athompson.scrap.entities.engagement.EngagementScrapPlateau;
import fr.athompson.scrap.enums.EngagementType;
import fr.athompson.scrap.enums.SexeCompetitionType;

import java.util.List;
import java.util.Map;

public class EngagementScrapFactory {

    private EngagementScrapFactory() {
    }

    public static EngagementScrap createEngagement(EngagementType type, Map<SexeCompetitionType, List<CompetitionScrap>> competitionsEngagees) {
        return switch (type) {
            case CHAMPIONNAT -> new EngagementScrapChampionnat(competitionsEngagees);
            case COUPE -> new EngagementScrapCoupe(competitionsEngagees);
            case PLATEAU -> new EngagementScrapPlateau(competitionsEngagees);
            default -> throw new RuntimeException("Erreur dans les types d'engagement");
        };
    }
}
