package fr.athompson.ffbbscraper;

import fr.athompson.ffbbscraper.scrapers.classement.APIClassementScraper;
import fr.athompson.ffbbscraper.scrapers.journee.APIJourneeScraper;
import fr.athompson.ffbbscraper.scrapers.organisation.APIOrganisationScraper;
import fr.athompson.ffbbscraper.utils.CompteurAppelSingleton;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.time.LocalTime;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class FfbbScraperApplication {

    final APIOrganisationScraper organisationScraper;

    final APIClassementScraper classementScraper;

    final APIJourneeScraper journeeScraper;

    final ChromeDriver driver;

    public static void main(String[] args) {
        SpringApplication.run(FfbbScraperApplication.class, args);
    }

    //@Scheduled(fixedRate = 5000)
    @PostConstruct
    public void call() {
        try {
            LocalTime previous = LocalTime.now();
            //AS HESDIGNEUL-HESDIN L'ABBE
            //var t = organisationScraper.getData("28ee");
            var t = journeeScraper.getData("b5e6211fffeab5e62121f92a7");
            //Coquelles
            //var t = organisationScraper.getData("1a961afb98b");28ee
            ///var t = classementScraper.scrap("b5e6211fe7d7b5e62121c154");
            var end = LocalTime.now();
            var duration = Duration.between(previous, end);
            var formattedDuration = String.format("%02d:%02d", duration.toMinutesPart(),
                    duration.toSecondsPart());
            System.out.println("finiiiii " + CompteurAppelSingleton.getInstance().getNbAppel() + " " + formattedDuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
