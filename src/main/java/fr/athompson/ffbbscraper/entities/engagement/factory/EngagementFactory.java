package fr.athompson.ffbbscraper.entities.engagement.factory;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.EngagementChampionnat;
import fr.athompson.ffbbscraper.entities.engagement.EngagementCoupe;
import fr.athompson.ffbbscraper.entities.engagement.EngagementPlateau;
import fr.athompson.ffbbscraper.enums.EngagementType;
import fr.athompson.ffbbscraper.enums.SexeCompetitionType;

import java.util.List;
import java.util.Map;

public class EngagementFactory {

    private EngagementFactory() {
    }

    public static Engagement createEngagement(EngagementType type, Map<SexeCompetitionType, List<Competition>> competitionsEngagees){
        return switch (type) {
            case CHAMPIONNAT -> new EngagementChampionnat(competitionsEngagees);
            case COUPE -> new EngagementCoupe(competitionsEngagees);
            case PLATEAU -> new EngagementPlateau(competitionsEngagees);
            default -> throw new RuntimeException("Erreur dans les types d'engagement");
        };
    }
}
