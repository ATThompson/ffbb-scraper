package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.CompetitionScrap;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
public class EngagementScrapCoupe extends EngagementScrap {
    public EngagementScrapCoupe(CompetitionScrap competitionEngagee) {
        super(competitionEngagee);
    }
}
