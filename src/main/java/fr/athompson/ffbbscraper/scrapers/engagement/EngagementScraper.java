package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.utils.URIBuilder;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.factory.EngagementFactory;
import fr.athompson.ffbbscraper.enums.EngagementType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EngagementScraper implements APIEngagementScraper {

    final String URI_ENGAGEMENT = "https://resultats.ffbb.com/organisation/engagements/";

    @Override
    public List<Engagement> scrap(String idOrganisation){
        String uri = URIBuilder.build(URI_ENGAGEMENT,idOrganisation);
        try {
            Document doc = Jsoup.connect(uri).get();
            Elements htmlEngagements = doc.select("table.liste");
            List<Engagement> engagements = new ArrayList<>();
            for (Element htmlEngagement : htmlEngagements) {
                var engagement = creerEngagement(htmlEngagement);
                engagements.add(engagement);
            }
            System.out.println(engagements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<Engagement>();
    }

    private Engagement creerEngagement(Element htmlEngagement){
       var htmlTypeEngagement = htmlEngagement
               .getElementsByClass("titre-bloc")
               .select("td")
               .first()
               .text()
               .trim();

        var htmlSexeChampionnat = htmlEngagement
                .getElementsByClass("tit-3")
                .first()
                .text()
                .trim();

        return EngagementFactory.createEngagement(EngagementType.findByLibelleHtml(htmlTypeEngagement));
    }

}
