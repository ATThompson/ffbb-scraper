package fr.athompson.entities;

import fr.athompson.domain.entities.Rencontre;

public record ProchainMatchResponse(CompetitionResponse competition, Rencontre rencontre) {
}
