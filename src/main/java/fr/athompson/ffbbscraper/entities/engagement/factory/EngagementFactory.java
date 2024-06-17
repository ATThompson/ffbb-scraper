package fr.athompson.ffbbscraper.entities.engagement.factory;

import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.EngagementChampionnat;
import fr.athompson.ffbbscraper.entities.engagement.EngagementCoupe;
import fr.athompson.ffbbscraper.entities.engagement.EngagementPlateau;
import fr.athompson.ffbbscraper.enums.EngagementType;

public class EngagementFactory {

    private EngagementFactory() {
    }

    public static Engagement createEngagement(EngagementType type){
        switch (type){
            case EngagementType.CHAMPIONNAT:
                return new EngagementChampionnat();
            case COUPE:
                return new EngagementCoupe();
            case PLATEAU:
                return new EngagementPlateau();
            default:
                throw new RuntimeException("Erreur dans les types d'engagement");
        }
    }
}
