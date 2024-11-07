package fr.athompson.cron.entities.engagement.factory;

import fr.athompson.cron.entities.CompetitionScrap;
import fr.athompson.cron.entities.engagement.EngagementScrap;
import fr.athompson.cron.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.cron.entities.engagement.EngagementScrapCoupe;
import fr.athompson.cron.entities.engagement.EngagementScrapPlateau;
import fr.athompson.cron.enums.EngagementType;

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
