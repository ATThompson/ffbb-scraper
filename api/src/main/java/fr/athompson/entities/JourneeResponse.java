package fr.athompson.entities;

import java.util.List;

public record JourneeResponse(Integer numeroJournee, List<RencontreResponse> rencontres) {
}
