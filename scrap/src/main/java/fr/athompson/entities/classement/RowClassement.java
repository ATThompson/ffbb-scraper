package fr.athompson.entities.classement;

import fr.athompson.entities.Equipe;
import lombok.Builder;

@Builder
public record RowClassement(
        Integer position,
        Equipe equipe,
        Integer points,
        Integer matchJoues,
        Integer matchGagnes,
        Integer matchPerdus,
        Integer matchNuls,
        Integer matchPenalite,
        Integer matchForfait,
        Integer pointsMarques,
        Integer pointsEncaisses,
        Integer difference
) {
}
