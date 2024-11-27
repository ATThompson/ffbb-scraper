package fr.athompson.entities;

import fr.athompson.domain.entities.Equipe;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RencontreResponse(Integer numeroRencontre,
                        LocalDateTime date,
                        EquipeResponse equipeDomicile,
                        EquipeResponse equipeVisiteur,
                        Integer scoreDomicile,
                        Integer scoreVisiteur) {
}
