package fr.athompson.api;

import fr.athompson.domain.services.organisation.GetAllOrganisations;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organisations/{idOrganisation}/competitionsEngagees")
@AllArgsConstructor
public class APIEngagement{


    @GetMapping
    public String competitionsEngagees(@PathVariable String idOrganisation){
        return idOrganisation;
    }
}
