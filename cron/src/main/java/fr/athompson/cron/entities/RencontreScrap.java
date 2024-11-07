package fr.athompson.cron.entities;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RencontreScrap(Integer numeroRencontre,
                             LocalDateTime date,
                             EquipeScrap equipeDomicile,
                             EquipeScrap equipeVisiteur,
                             Integer scoreDomicile,
                             Integer scoreVisiteur) {
}
