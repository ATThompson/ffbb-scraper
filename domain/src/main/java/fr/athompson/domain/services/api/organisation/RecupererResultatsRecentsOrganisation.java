package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.DernierResultat;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherDernierResultatOrganisationCompetition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RecupererResultatsRecentsOrganisation {

    SPIChercherCompetitionsEngagees chercherCompetitionsEngagees;

    SPIChercherDernierResultatOrganisationCompetition chercherRencontresCompetitionEquipe;

    public List<DernierResultat> execute(String idOrganisation) {
        List<DernierResultat> dernierResultats = new ArrayList<>();
        Instant begin = Instant.now();

        List<Competition> competitionsEngagees = chercherCompetitionsEngagees.chercher(idOrganisation);
        Instant afterCompetition = Instant.now();

        log.info("Elapsed time after competition : {} ms", Duration.between(begin, afterCompetition).toMillis());
        for (Competition competition : competitionsEngagees) {
            Optional<Rencontre> rencontreOptional = chercherRencontresCompetitionEquipe.chercher(idOrganisation, competition);


            if (rencontreOptional.isPresent()) {
                var dernierResultat = DernierResultat.builder()
                        .competition(competition)
                        .rencontre(rencontreOptional.get())
                        .build();
                dernierResultats.add(dernierResultat);
            }
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(begin, finish).toMillis();
        log.info("Elapsed time: {} ms", timeElapsed);

        return dernierResultats;

    }

}
