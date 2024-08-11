package fr.athompson.domain.entities;

import java.util.List;

public record Journee(List<Rencontre> recontres, Integer idJournee) {
}
