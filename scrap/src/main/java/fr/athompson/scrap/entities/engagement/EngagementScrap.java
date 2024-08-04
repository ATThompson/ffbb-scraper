package fr.athompson.scrap.entities.engagement;

import fr.athompson.scrap.entities.CompetitionScrap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EngagementScrap {
    private CompetitionScrap competitionEngagee;
    private String poule;

    //TODO: Voir pour ajouter le niveau depart/regional plus la division 1 2 3
    // et définir ca comme une liste de toutes ces infos
    //Avec la poule , le nom
    // ca ne serait plus un liste de competition mais une seule competition et une liste plus grande d'engagements
    public EngagementScrap() {
    }

    public EngagementScrap(CompetitionScrap competitionEngagee, String poule) {
        this.competitionEngagee = competitionEngagee;
        this.poule = poule;
    }

}
