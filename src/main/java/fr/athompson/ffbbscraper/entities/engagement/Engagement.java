package fr.athompson.ffbbscraper.entities.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.entities.Competition;

import java.util.List;
import java.util.Map;

public abstract class Engagement {
    protected Map<SexeCompetitionType, List<Competition>> competitionsEngagees;

    public Engagement() {
    }

    public Engagement(Map<SexeCompetitionType, List<Competition>> competitionsEngagees) {
        this.competitionsEngagees = competitionsEngagees;
    }

}
