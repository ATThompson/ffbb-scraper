package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class EngagementPlateau extends Engagement {

    public EngagementPlateau(Competition competitionEngagee) {
        super(competitionEngagee);
    }

    public EngagementPlateau() {
    }
}
