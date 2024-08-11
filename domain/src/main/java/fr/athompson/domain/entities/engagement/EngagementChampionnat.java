package fr.athompson.domain.entities.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.SexeCompetitionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class EngagementChampionnat extends Engagement {
    public EngagementChampionnat(Competition competitionEngagee) {
        super(competitionEngagee);
    }

    public EngagementChampionnat() {
        super();
    }
}
