package fr.athompson.domain.services.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.engagement.Engagement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCompetitionsEngagees {

    SPICompetitionsEngagees spiCompetitionsEngagees;

    public List<Competition> execute(String idOrganisation){
        return spiCompetitionsEngagees.execute(idOrganisation);
    }
}
