package fr.athompson.database.services.api.journee;

import fr.athompson.database.entities.JourneeDB;
import fr.athompson.database.mappers.JourneeMapperDB;
import fr.athompson.database.repositories.JourneeRepository;
import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.services.spi.SPIChercherJourneesCompetition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChercherJourneeCompetitions implements SPIChercherJourneesCompetition {

    JourneeRepository journeeRepository;

    JourneeMapperDB journeeMapperDB;

    @Override
    public List<Journee> chercher(String idChampionnat, String idDivision, String idPoule) {
        List<JourneeDB> journeesDB = journeeRepository.findAllByCompetition_ChampionnatIdHtmlAndCompetition_DivisionIdHtmlAndCompetition_PouleIdHtml(idChampionnat, idDivision, idPoule).orElseGet(List::of);
        return journeesDB.stream().map(journeeMapperDB::toDomain).toList();
    }
}
