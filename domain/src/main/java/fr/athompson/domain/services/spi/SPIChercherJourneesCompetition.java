package fr.athompson.domain.services.spi;

import fr.athompson.domain.entities.Journee;

import java.util.List;

public interface SPIChercherJourneesCompetition {

    List<Journee> chercher(String idChampionnat, String idDivision, String idPoule);
}
