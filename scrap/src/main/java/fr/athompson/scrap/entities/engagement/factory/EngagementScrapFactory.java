package fr.athompson.scrap.entities.engagement.factory;

import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.scrap.entities.engagement.EngagementScrapCoupe;
import fr.athompson.scrap.entities.engagement.EngagementScrapPlateau;
import fr.athompson.scrap.enums.EngagementType;

public class EngagementScrapFactory {

    private EngagementScrapFactory() {
    }

    public static EngagementScrap createEngagement(EngagementType type, CompetitionScrap competitionsEngagee) {
        return switch (type) {
            case CHAMPIONNAT -> new EngagementScrapChampionnat(competitionsEngagee);
            case COUPE -> new EngagementScrapCoupe(competitionsEngagee);
            case PLATEAU -> new EngagementScrapPlateau(competitionsEngagee);
            default -> throw new RuntimeException("Erreur dans les types d'engagement");
        };
    }
}
