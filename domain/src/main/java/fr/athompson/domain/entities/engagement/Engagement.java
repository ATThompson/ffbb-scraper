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
    private String poule;

    public Engagement(Competition competitionEngagee, String poule) {
        this.competitionEngagee = competitionEngagee;
        this.poule = poule;
    }

    public Engagement() {
    }
}
