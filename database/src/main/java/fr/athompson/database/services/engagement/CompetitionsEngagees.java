package fr.athompson.database.services.engagement;

import fr.athompson.database.entities.EngagementDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.mappers.EngagementMapperDB;
import fr.athompson.database.repositories.EngagementRepository;
import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.services.engagement.SPICompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionsEngagees implements SPICompetitionsEngagees {

    EngagementRepository engagementRepository;
    EngagementMapperDB engagementMapperDB;

    CompetitionMapperDB competitionMapperDB;

    @Override
    public List<Engagement> execute(String idOrganisation) {
        return engagementRepository.findByOrganisation_OrganisationIdHtml(idOrganisation).stream().map(EngagementDB::getCompetition).map();
    }
}
