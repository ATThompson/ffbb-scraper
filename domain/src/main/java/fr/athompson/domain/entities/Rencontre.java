package fr.athompson.domain.entities;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Rencontre(Integer numeroRencontre,
                        LocalDateTime date,
                        Equipe equipeDomicile,
                        Equipe equipeVisiteur,
                        Integer scoreDomicile,
                        Integer scoreVisiteur) {
}
