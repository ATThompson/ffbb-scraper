package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.entities.engagement.EngagementChampionnat;
import fr.athompson.domain.entities.engagement.EngagementCoupe;
import fr.athompson.domain.entities.engagement.EngagementPlateau;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.scrap.entities.engagement.EngagementScrapCoupe;
import fr.athompson.scrap.entities.engagement.EngagementScrapPlateau;
import org.mapstruct.*;

public interface EngagementMapper {


   EngagementChampionnat toDomain(EngagementScrapChampionnat engagementScrap);

    EngagementCoupe toDomain(EngagementScrapCoupe engagementScrap);

    EngagementPlateau toDomain(EngagementScrapPlateau engagementScrap);

    default Engagement mapToEngagement(EngagementScrap engagementScrap) {
        if (engagementScrap instanceof EngagementScrapChampionnat) {
            return toDomain((EngagementScrapChampionnat) engagementScrap);
        } else if (engagementScrap instanceof EngagementScrapCoupe) {
            return toDomain((EngagementScrapCoupe) engagementScrap);
        } else if (engagementScrap instanceof EngagementScrapPlateau) {
            return toDomain((EngagementScrapPlateau) engagementScrap);
        } else {
            return null;
        }
    }

}
