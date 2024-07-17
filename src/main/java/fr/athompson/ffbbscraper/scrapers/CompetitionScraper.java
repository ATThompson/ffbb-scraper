package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.entities.classement.Classement;
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

    public Competition scrap(Document doc) {
        //scrap le nombre de pages
        //https://resultats.ffbb.com/championnat/journees/b5e62120122bb5e6212216b9.html
        //https://resultats.ffbb.com/championnat/journees/b5e6211fe7d7b5e62121c154.html
        //scrap le classement
        //Scrap toutes les journées
        var journees = new ArrayList<Journee>();
        Classement classement = null;
        try {
            //Element table = doc.select("table.liste").first();
            Element td = doc.getElementById("idTableCoupeChampionnat");
            Element element = td.getElementById("idTdPoule");
            Element elt = element.select("option[selected]").first();
            if (null == elt) {
                elt = element.select("input").first();
            }
            String paramJournee = elt.attr("value").substring(9, 33);
            //b5e6211fe7d7b5e62121c154
            Integer nbPages = pageScaper.getData(paramJournee);
            log.info("nbPages " + nbPages);
            //Pré régionale masculine(Poule B)
            //idOrganisation b5e6211fe7d7
            //idDivision 200000002844631
            //idPoule 200000002965844
            for (int page = 1; page <= nbPages; page++) {
                journees.add(journeeScraper.getData(paramJournee + Integer.toHexString(page)));
            }
            //classement = classementScraper.scrap(paramJournee);
        } catch (Exception e) {
            log.error(e.getMessage());
            //throw new RuntimeException(e);
        }
        return new Competition(classement, journees, null);
    }
}
