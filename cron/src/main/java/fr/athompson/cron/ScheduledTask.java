package fr.athompson.cron;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@AllArgsConstructor
@RestController
public class ScheduledTask {
/**
    SPIGetAllCompetitions getAllCompetitions;

    SPISaveCompetition saveCompetition;

    SPIGetCompetition getCompetition;
*/
    //@Scheduled(fixedRate = 5000)
    @GetMapping("/cron")
    public void test() {
      /**  var competitions = getAllCompetitions.execute();
        log.info("Nombre de competitions " + competitions.size());
        for(CompetitionScrap competition : competitions) {
            String idOrganisation = competition.idOrganisation();
            String idDivision = competition.idDivision();
            String idPoule = competition.idPoule();
            String libellePoule = competition.poule();
            log.info("CRON : idOrganisation " + idOrganisation + " idDivision " + idDivision + " idPoule " + idPoule);
            log.info("Début du scraping pour la competition ");
            var competitionScrap = getCompetition.execute(idOrganisation,idDivision,idPoule,libellePoule);
            log.info("Fin scraping");
            log.info("Début sauvegarde");
            saveCompetition.execute(competitionScrap);
            log.info("Fin sauvegarde");
            return;
        }

*/

    }
}
