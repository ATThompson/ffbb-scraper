package fr.athompson.scrap.entities.classement;

import fr.athompson.scrap.entities.EquipeScrap;
import lombok.Builder;

@Builder
public record RowClassementScrap(
        Integer position,
        EquipeScrap equipe,
        Integer points,
        Integer matchJoues,
        Integer matchGagnes,
        Integer matchPerdus,
        Integer matchNuls,
        Integer matchPenalites,
        Integer matchForfaits,
        Integer pointsMarques,
        Integer pointsEncaisses,
        Integer pointsDifference
) {
}
