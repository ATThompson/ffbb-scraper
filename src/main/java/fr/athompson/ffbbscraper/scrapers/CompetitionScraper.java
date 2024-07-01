package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CompetitionScraper extends Scraper implements APICompetitionScraper {

    final String URI = "https://resultats.ffbb.com/championnat/{0}.html?r={1}&d={2}";

    public Competition scrap(String idOrganisation, String idDivision, String idPoule){
        String uri = URIBuilder.build(URI,idOrganisation,idDivision,idPoule);

        //scrap le nombre de pages
        //scrap le classement
        //Scrap toutes les journées

        try {

            Document doc = getDocument(uri);
            Element table = doc.select("table.liste").first();
            Element td = doc.getElementById("idTableCoupeChampionnat");
            Element element = td.getElementById("idTdPoule");
            Element elt = element.select("option[selected]").first();

            String code = elt.attr("value");

                    System.out.println(table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
