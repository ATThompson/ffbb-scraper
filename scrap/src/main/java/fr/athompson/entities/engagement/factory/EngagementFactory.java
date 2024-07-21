package fr.athompson.entities.engagement.factory;

import fr.athompson.entities.Competition;
import fr.athompson.entities.engagement.Engagement;
import fr.athompson.entities.engagement.EngagementChampionnat;
import fr.athompson.entities.engagement.EngagementCoupe;
import fr.athompson.entities.engagement.EngagementPlateau;
import fr.athompson.enums.EngagementType;
import fr.athompson.enums.SexeCompetitionType;

import java.util.List;
import java.util.Map;

public class EngagementFactory {

    private EngagementFactory() {
    }

    public static Engagement createEngagement(EngagementType type, Map<SexeCompetitionType, List<Competition>> competitionsEngagees) {
        return switch (type) {
            case CHAMPIONNAT -> new EngagementChampionnat(competitionsEngagees);
            case COUPE -> new EngagementCoupe(competitionsEngagees);
            case PLATEAU -> new EngagementPlateau(competitionsEngagees);
            default -> throw new RuntimeException("Erreur dans les types d'engagement");
        };
    }
}
