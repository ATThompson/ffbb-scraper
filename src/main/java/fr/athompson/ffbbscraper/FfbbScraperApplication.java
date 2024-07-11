package fr.athompson.ffbbscraper;

import fr.athompson.ffbbscraper.scrapers.journee.JourneeScraper;
import fr.athompson.ffbbscraper.scrapers.organisation.APIOrganisationScraper;
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
            var t = organisationScraper.scrap("1a961afb98b");
            System.out.print("finiiiii");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            driver.quit();
        }
    }


}
