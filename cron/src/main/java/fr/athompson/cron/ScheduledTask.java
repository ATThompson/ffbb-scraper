package fr.athompson.cron;

import fr.athompson.cron.spi.SPIGetAllCompetitions;
import fr.athompson.cron.spi.SPIGetCompetition;
import fr.athompson.cron.spi.SPISaveCompetition;
import fr.athompson.domain.entities.Competition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@AllArgsConstructor
@RestController
public class ScheduledTask {

    SPIGetAllCompetitions getAllCompetitions;

    SPISaveCompetition saveCompetition;

    SPIGetCompetition getCompetition;

    //@Scheduled(fixedRate = 5000)
    @GetMapping("/cron")
    public void test() {
        var competitions = getAllCompetitions.execute();
        log.info("Nombre de competitions " + competitions.size());
        for(Competition competition : competitions) {
            String idOrganisation = competition.idOrganisation();
            String idDivision = competition.idDivision();
            String idPoule = competition.idPoule();
            log.info("CRON : idOrganisation " + idOrganisation + " idDivision " + idDivision + " idPoule " + idPoule);
            log.info("Début du scraping pour la competition ");
            getCompetition.execute(idOrganisation,idDivision,idPoule);
            log.info("Fin scraping");
            log.info("Début sauvegarde");
            saveCompetition.execute(competition);
            log.info("Fin sauvegarde");
        }



    }
}
