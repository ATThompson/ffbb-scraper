package fr.athompson.ffbbscraper.scrapers.competition;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.entities.classement.Classement;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.scrapers.classement.APIClassementScraper;
import fr.athompson.ffbbscraper.scrapers.journee.APIJourneeScraper;
import fr.athompson.ffbbscraper.scrapers.journee.JourneeScraper;
import fr.athompson.ffbbscraper.scrapers.page.APIPageScaper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class CompetitionScraper extends Scraper<Competition> implements APICompetitionScraper {

    APIPageScaper pageScaper;

    APIJourneeScraper journeeScraper;

    APIClassementScraper classementScraper;

    public CompetitionScraper(@Value("${ffbb.url.competition}") String uri, ChromeDriver driver, JourneeScraper journeeScraper, APIPageScaper pageScaper, APIClassementScraper classementScraper) {
        super(uri, driver);
        this.journeeScraper = journeeScraper;
        this.pageScaper = pageScaper;
        this.classementScraper = classementScraper;
    }

    protected Competition scrap(Document doc) {
        var journees = new ArrayList<Journee>();
        String identifiantClassement = getIdentifiantClassement(doc);
        Integer nbPages = pageScaper.getData(identifiantClassement);
        log.info("Nombre de page : {} ", nbPages);
        for (int page = 1; page <= nbPages; page++) {
            journees.add(journeeScraper.getData(identifiantClassement + Integer.toHexString(page)));
        }
        Classement classement = classementScraper.getData(identifiantClassement);
        return new Competition(classement, journees, null);
    }

    private String getIdentifiantClassement(Document doc) {
        Element td = doc.getElementById("idTableCoupeChampionnat");
        Element element = td.getElementById("idTdPoule");
        Element elt = element.select("option[selected]").first();
        if (null == elt) {
            elt = element.select("input").first();
        }
        String identifiantClassement = elt.attr("value").substring(9, 33);
        return identifiantClassement;
    }
}
