package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.DernierResultat;
import fr.athompson.domain.entities.ProchainMatch;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherRencontresCompetitionEquipe;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecupererProchainesRencontresOrganisation {

    SPIChercherCompetitionsEngagees chercherCompetitionsEngagees;

    SPIChercherRencontresCompetitionEquipe chercherRencontresCompetitionEquipe;


    public List<ProchainMatch> execute(String idOrganisation) {
        List<ProchainMatch> prochainMatchs = new ArrayList<>();
        List<Competition> competitionsEngagees = chercherCompetitionsEngagees.chercher(idOrganisation);

        for(Competition competition : competitionsEngagees){
            List<Rencontre> rencontres = chercherRencontresCompetitionEquipe.chercher(idOrganisation, competition.idChampionnat(), competition.idDivision(), competition.idPoule() );

            Optional<Rencontre> rencontre = trouverProchainMatch(rencontres);
            if(rencontre.isPresent()){
                var prochainMatch = ProchainMatch.builder()
                        .competition(competition)
                        .rencontre(rencontre.get())
                        .build();
                prochainMatchs.add(prochainMatch);
            }
        }


        return prochainMatchs;
    }

    private Optional<Rencontre> trouverProchainMatch(List<Rencontre> rencontres) {
        return rencontres.stream()
                .sorted( Comparator.comparing(Rencontre::date) )
                .filter( r ->   r.scoreDomicile() == 0 && r.scoreVisiteur() == 0 )
                .findFirst();
    }
}
