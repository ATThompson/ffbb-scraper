package fr.athompson.cron.entities.classement;

import fr.athompson.cron.entities.EquipeScrap;
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
