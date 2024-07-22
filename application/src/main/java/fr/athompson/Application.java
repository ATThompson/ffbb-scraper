package fr.athompson;

import fr.athompson.scrap.scrapers.api.APIClassementScraper;
import fr.athompson.scrap.scrapers.api.APIJourneeScraper;
import fr.athompson.scrap.scrapers.api.APIOrganisationScraper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class Application {

    final APIOrganisationScraper organisationScraper;

    final APIClassementScraper classementScraper;

    final APIJourneeScraper journeeScraper;

    final ChromeDriver driver;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Scheduled(fixedRate = 5000)
    @PostConstruct
    public void call() {
        try {

            LocalTime previous = LocalTime.now();
            //AS HESDIGNEUL-HESDIN L'ABBE
            //var t = organisationScraper.getData("28ee");
            //var t = journeeScraper.getData("b5e6211fffeab5e62121f92a7");
            //Coquelles
            //var t = organisationScraper.getData("1a961afb98b");28ee
            ///var t = classementScraper.scrap("b5e6211fe7d7b5e62121c154");
            var end = LocalTime.now();
            var duration = Duration.between(previous, end);
            var formattedDuration = String.format("%02d:%02d", duration.toMinutesPart(),
                    duration.toSecondsPart());
            //System.out.println("finiiiii " + CompteurAppelSingleton.getInstance().getNbAppel() + " " + formattedDuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("finally scope");
            // driver.quit();
        }
    }

}
