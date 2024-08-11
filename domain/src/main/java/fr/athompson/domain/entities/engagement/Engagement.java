package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public abstract class Engagement {
    private Competition competitionEngagee;

    public Engagement(Competition competitionEngagee) {
        this.competitionEngagee = competitionEngagee;
    }

    public Engagement() {
    }
}
