package fr.athompson.domain.services.api.competition;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecupererCompetitionsEngagees {

    SPIChercherCompetitionsEngagees chercherCompetitionsEngagees;

    public List<Competition> execute(String idOrganisation){
        return chercherCompetitionsEngagees.chercher(idOrganisation);
    }
}
