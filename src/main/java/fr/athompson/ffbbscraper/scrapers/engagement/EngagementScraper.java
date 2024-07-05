package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.ScrapUtils;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.factory.EngagementFactory;
import fr.athompson.ffbbscraper.enums.EngagementType;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class EngagementScraper extends Scraper implements APIEngagementScraper {

    final String URI_ENGAGEMENT = "https://resultats.ffbb.com/organisation/engagements/";

    @Override
    public List<Engagement> scrap(String idOrganisation){
        String uri = URIBuilder.build(URI_ENGAGEMENT,idOrganisation);
        try {
            Document doc = getDocument(uri);
            Elements htmlEngagements = doc.select("table.liste");
            List<Engagement> engagements = new ArrayList<>();
            for (Element htmlEngagement : htmlEngagements) {
                var engagement = creerEngagement(htmlEngagement);
                engagements.add(engagement);
            }
            System.out.println(engagements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<Engagement>();
    }

    private Engagement creerEngagement(Element htmlEngagement){
       var htmlTypeEngagement =  ScrapUtils.getFirstElementText(
               htmlEngagement
                       .getElementsByClass("titre-bloc")
                       .first()
               ,"td");


       //Parcourir l'engagement et créer les compétitions adéquates
        var equipesEngages = htmlEngagement
               .getElementsByTag("tr")
               .not(".titre-bloc");

        for(Element equipeEngage : equipesEngages){
            String firstTableCellText =  ScrapUtils.getFirstElementText(equipeEngage,"td");
            if(equipeEngage.hasClass("tit-3"))
                log.info(SexeCompetitionType.findByLibelleHtml(firstTableCellText));
            else if(equipeEngage.hasClass("altern-2") || equipeEngage.hasClass("no-altern-2")){
                //Créer des compétitions
                log.info(firstTableCellText);
            }

        }

        return EngagementFactory.createEngagement(EngagementType.findByLibelleHtml(htmlTypeEngagement),null);
    }

}
