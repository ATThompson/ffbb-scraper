package fr.athompson.ffbbscraper.scrapers.journee;

import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JourneeScraper extends Scraper {
    final String URI = "https://resultats.ffbb.com/championnat/{0}.html?r={1}&d={2}&p={3}";

    public Journee scrap(String idOrganisation, String idDivision, String idPoule, String page){

                      String uri = URIBuilder.build(URI,idOrganisation,idDivision,idPoule,page);
        try {

            Document doc = getDocument(uri);
            Element table = doc.select("table.liste").first();
            Element td = doc.getElementById("idTableCoupeChampionnat");
            Element element = td.getElementById("idTdPoule");
            Element elt = element.select("option[selected]").first();
            System.out.println(table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };


}
