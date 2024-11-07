package fr.athompson.database.services.engagement;

import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.entities.EngagementDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.mappers.EngagementMapperDB;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.database.repositories.EngagementRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.services.engagement.SPICompetitionsEngagees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompetitionsEngagees implements SPICompetitionsEngagees {

    CompetitionMapperDB competitionMapperDB;

    CompetitionRepository competitionRepository;

    @Override
    public List<Competition> execute(String idOrganisation) {
        Optional<List<CompetitionDB>> competitionsDB = competitionRepository.findAllByEngagements_Organisation_organisationIdHtml(idOrganisation);
        return competitionsDB.map(competitionDBS -> competitionDBS.stream().map(c -> competitionMapperDB.toDomainUniquementSimpleChamp(c)).toList()).orElseGet(List::of);
    }
}
