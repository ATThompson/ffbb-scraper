package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.DernierResultat;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherRencontresCompetitionEquipe;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecupererResultatsRecentsOrganisation {

    SPIChercherCompetitionsEngagees chercherCompetitionsEngagees;

    SPIChercherRencontresCompetitionEquipe chercherRencontresCompetitionEquipe;

    public List<DernierResultat> execute(String idOrganisation){
        List<DernierResultat> dernierResultats = new ArrayList<>();

        List<Competition> competitionsEngagees = chercherCompetitionsEngagees.chercher(idOrganisation);
        for(Competition competition : competitionsEngagees){
            List<Rencontre> rencontres = chercherRencontresCompetitionEquipe.chercher(idOrganisation, competition.idChampionnat(), competition.idDivision(), competition.idPoule() );

            Optional<Rencontre> rencontre = trouverDerniereRencontre(rencontres);

            if(rencontre.isPresent()){
                var dernierResultat = DernierResultat.builder()
                        .competition(competition)
                        .rencontre(rencontre.get())
                        .build();
                dernierResultats.add(dernierResultat);
            }
        }

        return dernierResultats;

    }

    private Optional<Rencontre> trouverDerniereRencontre(List<Rencontre> rencontres) {
        return rencontres.stream()
                .filter(r -> r.date().plusDays(10).isAfter(LocalDateTime.now()))
                .sorted( Comparator.comparing(Rencontre::date).reversed() )
                .filter( r ->   r.scoreDomicile() != 0 || r.scoreVisiteur() != 0 )
                .findFirst();
    }

}
