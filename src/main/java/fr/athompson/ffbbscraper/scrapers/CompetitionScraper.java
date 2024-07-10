package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.scrapers.journee.APIJourneeScraper;
import fr.athompson.ffbbscraper.scrapers.page.APIPageScaper;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HexFormat;

@Component
@Slf4j
@AllArgsConstructor
public class CompetitionScraper extends Scraper implements APICompetitionScraper {

    APIPageScaper pageScaper;

    APIJourneeScraper journeeScraper;

    final String URI = "https://resultats.ffbb.com/championnat/{0}.html?r={1}&d={2}";

    public Competition scrap(String idOrganisation, String idDivision, String idPoule){
        String uri = URIBuilder.build(URI,idOrganisation,idDivision,idPoule);
        log.info("idOrganisation " +idOrganisation);
        log.info("idDivision " +idDivision);
        log.info("idPoule " +idPoule);
        //scrap le nombre de pages
        //https://resultats.ffbb.com/championnat/journees/b5e62120122bb5e6212216b9.html
        //https://resultats.ffbb.com/championnat/journees/b5e6211fe7d7b5e62121c154.html
        //scrap le classement
        //Scrap toutes les journées

        try {

            Document doc = getDocument(uri);
            //Element table = doc.select("table.liste").first();
            Element td = doc.getElementById("idTableCoupeChampionnat");
            Element element = td.getElementById("idTdPoule");
            Element elt = element.select("option[selected]").first();
            if(null == elt){
                elt = element.select("input").first();
            };
            String paramJournee = elt.attr("value").substring(9,33);
            //b5e6211fe7d7b5e62121c154
            Integer nbPages = pageScaper.scrap(paramJournee);
            log.info("nbPages "+nbPages);
            //Pré régionale masculine(Poule B)
            //idOrganisation b5e6211fe7d7
            //idDivision 200000002844631
            //idPoule 200000002965844
            for(int page = 1; page <= nbPages; page++){
                journeeScraper.scrap(paramJournee,page);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
