package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.SexeCompetitionType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class EngagementCoupe extends Engagement {
    public EngagementCoupe(Competition competitionEngagee, String poule) {
        super(competitionEngagee, poule);
    }

    public EngagementCoupe() {
    }
}
