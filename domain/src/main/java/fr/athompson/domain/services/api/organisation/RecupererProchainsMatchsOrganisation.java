package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.ProchainMatch;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherProchainMatchOrganisationCompetition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecupererProchainsMatchsOrganisation {

    SPIChercherCompetitionsEngagees chercherCompetitionsEngagees;

    SPIChercherProchainMatchOrganisationCompetition chercherProchainMatchOrganisationCompetition;


    public List<ProchainMatch> execute(String idOrganisation) {
        List<ProchainMatch> prochainMatchs = new ArrayList<>();
        List<Competition> competitionsEngagees = chercherCompetitionsEngagees.chercher(idOrganisation);

        for (Competition competition : competitionsEngagees) {
            Optional<Rencontre> rencontreOptional = chercherProchainMatchOrganisationCompetition.chercher(idOrganisation, competition);
            if (rencontreOptional.isPresent()) {
                var prochainMatchToAdd = ProchainMatch.builder()
                        .competition(competition)
                        .rencontre(rencontreOptional.get())
                        .build();
                prochainMatchs.add(prochainMatchToAdd);
            }
        }


        return prochainMatchs;
    }

}
