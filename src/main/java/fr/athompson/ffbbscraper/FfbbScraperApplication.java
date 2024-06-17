package fr.athompson.ffbbscraper;

import fr.athompson.ffbbscraper.scrapers.organisation.APIOrganisationScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class FfbbScraperApplication {

    APIOrganisationScraper organisationScraper;

    public FfbbScraperApplication(APIOrganisationScraper organisationScraper) {
        this.organisationScraper = organisationScraper;
    }

    public static void main(String[] args) {
        SpringApplication.run(FfbbScraperApplication.class, args);
    }

    @Scheduled(fixedRate = 5000)
    public void call(){
        organisationScraper.scrap("1a961afb98b");
    }

}
