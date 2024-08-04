package fr.athompson.domain.entities.classement;

import fr.athompson.domain.entities.Equipe;
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
        Integer matchPenalites,
        Integer matchForfaits,
        Integer pointsMarques,
        Integer pointsEncaisses,
        Integer pointsDifference
) {
}
