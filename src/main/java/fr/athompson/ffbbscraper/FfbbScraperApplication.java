package fr.athompson.ffbbscraper;

import fr.athompson.ffbbscraper.scrapers.journee.JourneeScraper;
import fr.athompson.ffbbscraper.scrapers.organisation.APIOrganisationScraper;
import fr.athompson.ffbbscraper.utils.CompteurAppelSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@EnableScheduling
public class FfbbScraperApplication {

    APIOrganisationScraper organisationScraper;

    @Autowired
    ChromeDriver driver;

    JourneeScraper journeeScraper = new JourneeScraper();
    public FfbbScraperApplication(APIOrganisationScraper organisationScraper) {
        this.organisationScraper = organisationScraper;
    }

    public static void main(String[] args) {
        SpringApplication.run(FfbbScraperApplication.class, args);
    }

    //@Scheduled(fixedRate = 5000)
    @PostConstruct
    public void call(){
        //journeeScraper.scrap("b5e6211fe7d7","200000002844631","200000002965844","22");
        try {
            LocalTime previous = LocalTime.now();
            var t = organisationScraper.scrap("1a961afb98b");
            var end = LocalTime.now();
            var duration = Duration.between(previous,end);
            var formattedDuration = String.format("%02d:%02d", duration.toMinutesPart(),
                    duration.toSecondsPart());
            System.out.println("finiiiii "+ CompteurAppelSingleton.getInstance().getNbAppel() + " "+ formattedDuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }
    }


}
