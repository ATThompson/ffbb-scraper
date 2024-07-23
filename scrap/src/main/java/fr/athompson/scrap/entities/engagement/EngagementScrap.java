package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.entities.CompetitionScrap;

import java.util.List;
import java.util.Map;

public abstract class EngagementScrap {
    protected Map<SexeCompetitionType, List<CompetitionScrap>> competitionsEngagees;

    //TODO: Voir pour ajouter le niveau depart/regional plus la division 1 2 3
    // et définir ca comme une liste de toutes ces infos
    //Avec la poule , le nom
    // ca ne serait plus un liste de competition mais une seule competition et une liste plus grande d'engagements
    public EngagementScrap() {
    }

    public EngagementScrap(Map<SexeCompetitionType, List<CompetitionScrap>> competitionsEngagees) {
        this.competitionsEngagees = competitionsEngagees;
    }

}
