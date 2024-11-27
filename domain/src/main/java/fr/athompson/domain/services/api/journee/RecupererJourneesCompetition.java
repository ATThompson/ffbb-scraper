package fr.athompson.domain.services.api.journee;

import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.services.spi.SPIChercherJourneesCompetition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecupererJourneesCompetition {

    SPIChercherJourneesCompetition chercherJourneesCompetition;

    public List<Journee> execute(String idChampionnat,String idDivision,String idPoule){
        return chercherJourneesCompetition.chercher(idChampionnat,idDivision,idPoule);
    }

}
