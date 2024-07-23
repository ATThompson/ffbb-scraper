package fr.athompson.domain.entities.engagement.factory;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.entities.engagement.EngagementChampionnat;
import fr.athompson.domain.entities.engagement.EngagementCoupe;
import fr.athompson.domain.entities.engagement.EngagementPlateau;
import fr.athompson.domain.enums.EngagementType;
import fr.athompson.domain.enums.SexeCompetitionType;

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
