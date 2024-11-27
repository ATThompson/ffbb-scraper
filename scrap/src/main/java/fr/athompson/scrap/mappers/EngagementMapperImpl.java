package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.engagement.EngagementChampionnat;
import fr.athompson.domain.entities.engagement.EngagementCoupe;
import fr.athompson.domain.entities.engagement.EngagementPlateau;
import fr.athompson.scrap.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.scrap.entities.engagement.EngagementScrapCoupe;
import fr.athompson.scrap.entities.engagement.EngagementScrapPlateau;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EngagementMapperImpl implements EngagementMapper {

    CompetitionMapper competitionMapper;

    @Override
    public EngagementChampionnat toDomain(EngagementScrapChampionnat engagementScrap) {
        if(null == engagementScrap) return null;
        var championnat = new EngagementChampionnat();
        championnat.setCompetitionEngagee(competitionMapper.toDomain(engagementScrap.getCompetitionEngagee()));
        return championnat;
    }

    @Override
    public EngagementCoupe toDomain(EngagementScrapCoupe engagementScrap) {
        if(null == engagementScrap) return null;
        var coupe = new EngagementCoupe();
        coupe.setCompetitionEngagee(competitionMapper.toDomain(engagementScrap.getCompetitionEngagee()));
        return coupe;
    }

    @Override
    public EngagementPlateau toDomain(EngagementScrapPlateau engagementScrap) {
        if(null == engagementScrap) return null;
        var plateau = new EngagementPlateau();
        plateau.setCompetitionEngagee(competitionMapper.toDomain(engagementScrap.getCompetitionEngagee()));
        return plateau;
    }
}
