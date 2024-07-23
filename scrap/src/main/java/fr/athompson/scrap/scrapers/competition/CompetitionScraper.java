package fr.athompson.scrap.scrapers.competition;

import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.JourneeScrap;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.scrapers.Scraper;
import fr.athompson.scrap.scrapers.classement.ClassementScraper;
import fr.athompson.scrap.scrapers.journee.JourneeScraper;
import fr.athompson.scrap.scrapers.page.PageScraper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class CompetitionScraper extends Scraper<CompetitionScrap> {

    PageScraper pageScaper;

    JourneeScraper journeeScraper;

    ClassementScraper classementScraper;

    public CompetitionScraper(@Value("${ffbb.url.competition}") String uri, ChromeDriver driver, JourneeScraper journeeScraper, PageScraper pageScaper, ClassementScraper classementScraper) {
        super(uri, driver);
        this.journeeScraper = journeeScraper;
        this.pageScaper = pageScaper;
        this.classementScraper = classementScraper;
    }

    protected CompetitionScrap scrap(Document doc) {
        var journees = new ArrayList<JourneeScrap>();
        String identifiantClassement = getIdentifiantClassement(doc);
        Integer nbPages = pageScaper.getData(identifiantClassement);
        log.info("Nombre de page : {} ", nbPages);
        for (int page = 1; page <= nbPages; page++) {
            journees.add(journeeScraper.getData(identifiantClassement + Integer.toHexString(page)));
        }
        ClassementScrap classementScrap = classementScraper.getData(identifiantClassement);
        return new CompetitionScrap(classementScrap, journees, null);
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
