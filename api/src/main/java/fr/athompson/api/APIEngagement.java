package fr.athompson.api;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.engagement.GetCompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("organisations/{idOrganisation}/competitionsEngagees")
@AllArgsConstructor
public class APIEngagement{

    GetCompetitionsEngagees getCompetitionsEngagees;

    @GetMapping
    public List<Competition> competitionsEngagees(@PathVariable String idOrganisation){
        return getCompetitionsEngagees.execute(idOrganisation);
    }
}
