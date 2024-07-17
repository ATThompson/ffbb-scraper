package fr.athompson.ffbbscraper.scrapers.engagement;

import fr.athompson.ffbbscraper.entities.Competition;
import fr.athompson.ffbbscraper.entities.engagement.Engagement;
import fr.athompson.ffbbscraper.entities.engagement.factory.EngagementFactory;
import fr.athompson.ffbbscraper.enums.EngagementType;
import fr.athompson.ffbbscraper.enums.SexeCompetitionType;
import fr.athompson.ffbbscraper.scrapers.APICompetitionScraper;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.ScrapUtils;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Component
public class EngagementScraper extends Scraper<List<Engagement>> implements APIEngagementScraper {

    APICompetitionScraper competitionScraper;

    public EngagementScraper(@Value("${ffbb.url.engagement}") String uri, ChromeDriver driver, APICompetitionScraper competitionScraper) {
        super(uri, driver);
        this.competitionScraper = competitionScraper;
    }

    private Engagement creerEngagement(Element htmlEngagement) throws Exception {
        var htmlTypeEngagement = ScrapUtils.getFirstElementText(htmlEngagement.getElementsByClass("titre-bloc").first(), "td");

        //Parcourir l'engagement et créer les compétitions adéquates
        var equipesEngages = htmlEngagement
                .getElementsByTag("tr")
                .not(".titre-bloc");

        var competitionsEngagees = new HashMap<SexeCompetitionType, List<Competition>>();

        SexeCompetitionType sexeCompetition = null;
        var competitions = new ArrayList<Competition>();
        for (Element equipeEngage : equipesEngages) {

            String firstTableCellText = ScrapUtils.getFirstElementText(equipeEngage, "td");
            if (equipeEngage.hasClass("tit-3")) {
                if (null != sexeCompetition) {
                    competitionsEngagees.put(sexeCompetition, competitions);
                    competitions = new ArrayList<>();
                }
                sexeCompetition = SexeCompetitionType.findByLibelleHtml(firstTableCellText);
                log.info(sexeCompetition);
            } else if (equipeEngage.hasClass("altern-2") || equipeEngage.hasClass("no-altern-2")) {
                var competition = creerCompetition(equipeEngage, htmlTypeEngagement);
                competitions.add(competition);
            }
        }
        competitionsEngagees.put(sexeCompetition, competitions);
        return EngagementFactory.createEngagement(EngagementType.findByLibelleHtml(htmlTypeEngagement), competitionsEngagees);
    }

    private Competition creerCompetition(Element equipeEngage, String htmlTypeEngagement) throws Exception {
        String lienCompetition = equipeEngage.select("a").attr("href");
        var params = new Parametres(lienCompetition);
        //TODO on focus sur le championnat pour le moment
        if (EngagementType.findByLibelleHtml(htmlTypeEngagement) == EngagementType.CHAMPIONNAT) {
            return competitionScraper.getData(params.getIdOrganisation(), params.getIdDivision(), params.getIdPoule());
        } else
            return null;
    }

    @Override
    public List<Engagement> scrap(Document doc) {
        var engagements = new ArrayList<Engagement>();
        try {
            Elements htmlEngagements = doc.select("table.liste");
            for (Element htmlEngagement : htmlEngagements) {
                var engagement = creerEngagement(htmlEngagement);
                engagements.add(engagement);
            }
            System.out.println(engagements);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return engagements;
    }
}
