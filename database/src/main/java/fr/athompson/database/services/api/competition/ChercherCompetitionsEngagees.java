package fr.athompson.database.services.api.competition;


import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.services.spi.competition.SPIChercherCompetitionsEngagees;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class ChercherCompetitionsEngagees implements SPIChercherCompetitionsEngagees {

    CompetitionRepository competitionRepository;

    CompetitionMapperDB competitionMapperDB;

    @Override
    public List<Competition> chercher(String idOrganisation) {
        Optional<List<CompetitionDB>> competitionsDB = competitionRepository.findAllByEngagements_Organisation_organisationIdHtml(idOrganisation);
        return competitionMapperDB.toDomainUniquementSimpleChamp(competitionsDB.orElseGet(List::of));
    }
}
