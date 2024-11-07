package fr.athompson.database.services.competition;

import fr.athompson.cron.entities.CompetitionScrap;
import fr.athompson.cron.spi.SPIGetAllCompetitions;
import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.repositories.CompetitionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class GetAllCompetitions implements SPIGetAllCompetitions {

    CompetitionRepository competitionRepository;

    CompetitionMapperDB competitionMapperDB;

    @Override
    public List<CompetitionScrap> execute() {
       List<CompetitionDB> competitionsDB =  competitionRepository.findAll();
       return competitionMapperDB.toMetaData(competitionsDB);
    }
}
