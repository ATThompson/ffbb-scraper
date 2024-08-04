package fr.athompson.cron;

import fr.athompson.cron.spi.SPIGetAllCompetitions;
import fr.athompson.cron.spi.SPIGetCompetition;
import fr.athompson.cron.spi.SPISaveCompetition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledTask {

    SPIGetAllCompetitions getAllCompetitions;

    SPISaveCompetition saveCompetition;

    SPIGetCompetition getCompetition;

    @Scheduled(fixedRate = 5000)
    public void test() {
        var competitions = getAllCompetitions.execute();
        log.info("Nombre de competitions " + competitions.size());
        var competition = getCompetition.execute(competitions.getFirst().idOrganisation(),
                competitions.getFirst().idDivision(),
                competitions.getFirst().idPoule()
                );
        log.info("Identification organisation " + competitions.get(0).idOrganisation());
        saveCompetition.execute(competition);

    }
}
