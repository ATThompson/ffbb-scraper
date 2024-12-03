package fr.athompson.api;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.DernierResultat;
import fr.athompson.domain.entities.ProchainMatch;
import fr.athompson.domain.services.api.competition.RecupererCompetitionsEngagees;
import fr.athompson.domain.services.api.organisation.*;
import fr.athompson.entities.CompetitionResponse;
import fr.athompson.entities.DernierResultatResponse;
import fr.athompson.entities.OrganisationResponse;
import fr.athompson.entities.ProchainMatchResponse;
import fr.athompson.mappers.CompetitionResponseMapper;
import fr.athompson.mappers.DernierResultatResponseMapper;
import fr.athompson.mappers.OrganisationResponseMapper;
import fr.athompson.mappers.ProchainMatchResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organisations")
@AllArgsConstructor
public class APIOrganisation {

    RecupererToutesOrganisations recupererToutesOrganisations;

    AjouterOrganisation ajouterOrganisation;

    ActualiserOrganisation actualiserOrganisation;

    OrganisationResponseMapper organisationResponseMapper;

    RecupererCompetitionsEngagees recupererCompetitionsEngagees;

    CompetitionResponseMapper competitionResponseMapper;

    RecupererResultatsRecentsOrganisation recupererResultatsRecentsOrganisation;

    DernierResultatResponseMapper dernierResultatResponseMapper;

    RecupererProchainsMatchsOrganisation recupererProchainsMatchsOrganisation;

    ProchainMatchResponseMapper prochainMatchResponseMapper;

    @GetMapping
    public List<OrganisationResponse> recupererToutes() {
        var organisations = recupererToutesOrganisations.execute();
        return organisations.stream().map(organisationResponseMapper::toResponse).toList();
    }

    //30ab pro fille et espoirs
    @GetMapping("/{idOrganisation}")
    public String ajouter(@PathVariable String idOrganisation) {
        ajouterOrganisation.execute(idOrganisation);
        return "OK";
    }

    @GetMapping("/{idOrganisation}/actualiser")
    public String actualiser(@PathVariable String idOrganisation) {
        actualiserOrganisation.execute(idOrganisation);
        return "OK";
    }


    @GetMapping("/{idOrganisation}/competitionsEngagees")
    public List<CompetitionResponse> competitionsEngagees(@PathVariable String idOrganisation) {
        List<Competition> competitions = recupererCompetitionsEngagees.execute(idOrganisation);
        return competitions.stream().map(competitionResponseMapper::toResponse).toList();
    }

    @GetMapping("/{idOrganisation}/resultatsRecents")
    public List<DernierResultatResponse> resultatsRecents(@PathVariable String idOrganisation) {
        List<DernierResultat> dernierResultats = recupererResultatsRecentsOrganisation.execute(idOrganisation);
        return dernierResultatResponseMapper.toResponse(dernierResultats);
    }

    @GetMapping("/{idOrganisation}/prochainsMatchs")
    public List<ProchainMatchResponse> prochainsMatchs(@PathVariable String idOrganisation) {
        List<ProchainMatch> prochainsMatchs = recupererProchainsMatchsOrganisation.execute(idOrganisation);
        return prochainMatchResponseMapper.toResponse(prochainsMatchs);
    }
}
