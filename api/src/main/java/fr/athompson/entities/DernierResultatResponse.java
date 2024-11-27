package fr.athompson.entities;

public record DernierResultatResponse(
        CompetitionResponse competition,
        RencontreResponse rencontre
) {
}
