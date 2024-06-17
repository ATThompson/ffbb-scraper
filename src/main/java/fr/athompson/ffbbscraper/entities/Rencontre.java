package fr.athompson.ffbbscraper.entities;

import java.time.LocalDateTime;

public record Rencontre(LocalDateTime date,
                        Equipe equipeDomicile,
                        Equipe equipeVisiteur,
                        Integer scoreDomicile,
                        Integer scoreVisiteur) {
}
