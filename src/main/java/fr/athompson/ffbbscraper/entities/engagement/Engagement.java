package fr.athompson.ffbbscraper.entities.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.entities.Competition;

import java.util.List;
import java.util.Map;

public abstract class Engagement {
    protected Map<SexeCompetitionType, List<Competition>> competitionsEngagees;

    //TODO: Voir pour ajouter le niveau depart/regional plus la division 1 2 3
    // et définir ca comme une liste de toutes ces infos
    //Avec la poule , le nom
    // ca ne serait plus un liste de competition mais une seule competition et une liste plus grande d'engagements
    public Engagement() {
    }

    public Engagement(Map<SexeCompetitionType, List<Competition>> competitionsEngagees) {
        this.competitionsEngagees = competitionsEngagees;
    }

}
