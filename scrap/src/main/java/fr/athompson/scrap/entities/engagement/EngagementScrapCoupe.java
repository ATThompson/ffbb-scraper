package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.entities.CompetitionScrap;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EngagementScrapCoupe extends EngagementScrap {
    public EngagementScrapCoupe(CompetitionScrap competitionEngagee) {
        super(competitionEngagee);
    }
}
