package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.scrapers.APICompetitionScraper;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.ScrapUtils;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.factory.EngagementFactory;
import fr.athompson.ffbbscraper.enums.EngagementType;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class EngagementScraper extends Scraper implements APIEngagementScraper {

    final String URI_ENGAGEMENT = "https://resultats.ffbb.com/organisation/engagements/{0}.html";

    APICompetitionScraper competitionScraper;

    @Override
    public List<Engagement> scrap(String idOrganisation){
        String uri = URIBuilder.build(URI_ENGAGEMENT,idOrganisation);
        var engagements = new ArrayList<Engagement>();
        try {
            Document doc = getDocument(uri);
            Elements htmlEngagements = doc.select("table.liste");
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

        return engagements;
    }

    private Engagement creerEngagement(Element htmlEngagement) throws Exception {
       var htmlTypeEngagement =  ScrapUtils.getFirstElementText(
               htmlEngagement
                       .getElementsByClass("titre-bloc")
                       .first()
               ,"td");


       //Parcourir l'engagement et créer les compétitions adéquates
        var equipesEngages = htmlEngagement
               .getElementsByTag("tr")
               .not(".titre-bloc");

        var competitionsEngagees = new HashMap<SexeCompetitionType,List<Competition>>();

        SexeCompetitionType sexeCompetition = null;
        var competitions = new ArrayList<Competition>();
        for(Element equipeEngage : equipesEngages){
            competitions.clear();
            String firstTableCellText =  ScrapUtils.getFirstElementText(equipeEngage,"td");
            if(equipeEngage.hasClass("tit-3")) {
                sexeCompetition = SexeCompetitionType.findByLibelleHtml(firstTableCellText);
                log.info(sexeCompetition);
            }
            else if(equipeEngage.hasClass("altern-2") || equipeEngage.hasClass("no-altern-2")){
                //Créer des compétitions
                log.info(firstTableCellText);
                String lienCompetition = equipeEngage.select("a").attr("href");
                var params = new Parametres(lienCompetition);
                Competition competition = competitionScraper.scrap(params.getIdOrganisation(), params.getIdDivision(),params.getIdPoule());
                competitions.add(competition);
            }
            competitionsEngagees.put(sexeCompetition,competitions);
        }

        return EngagementFactory.createEngagement(EngagementType.findByLibelleHtml(htmlTypeEngagement),competitionsEngagees);
    }

}
