package fr.athompson.scrap.entities;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RencontreScrap(Integer numeroRencontre,
                             LocalDateTime date,
                             EquipeScrap equipeScrapDomicile,
                             EquipeScrap equipeScrapVisiteur,
                             Integer scoreDomicile,
                             Integer scoreVisiteur) {
}
