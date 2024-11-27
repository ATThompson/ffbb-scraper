package fr.athompson.api;

import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.services.api.journee.RecupererJourneesCompetition;
import fr.athompson.entities.JourneeResponse;
import fr.athompson.mappers.JourneeResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/journees")
@AllArgsConstructor
public class APIJournee {

    RecupererJourneesCompetition recupererJourneesCompetition;

    JourneeResponseMapper journeeResponseMapper;

    @GetMapping("/{identifiantChampionnat}/{identifiantDivision}/{identifiantPoule}")
    public List<JourneeResponse> recupererJournees(@PathVariable String identifiantChampionnat,
                                                   @PathVariable String identifiantDivision,
                                                   @PathVariable String identifiantPoule ){
        List<Journee> journees = recupererJourneesCompetition.execute(identifiantChampionnat, identifiantDivision, identifiantPoule);
        return journeeResponseMapper.toResponse(journees);
    }

}
