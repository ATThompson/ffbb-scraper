package fr.athompson.cron.entities.engagement;

import fr.athompson.cron.entities.CompetitionScrap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EngagementScrap {
    private CompetitionScrap competitionEngagee;

    //TODO: Voir pour ajouter le niveau depart/regional plus la division 1 2 3
    // et définir ca comme une liste de toutes ces infos
    //Avec la poule , le nom
    // ca ne serait plus un liste de competition mais une seule competition et une liste plus grande d'engagements
    public EngagementScrap() {
    }

    public EngagementScrap(CompetitionScrap competitionEngagee) {
        this.competitionEngagee = competitionEngagee;
    }

}
