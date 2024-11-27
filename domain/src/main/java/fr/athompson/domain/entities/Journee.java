package fr.athompson.domain.entities;

import java.util.List;

public record Journee(List<Rencontre> rencontres, Integer idJournee) {
}
