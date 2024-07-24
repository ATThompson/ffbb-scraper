package fr.athompson.scrap.scrapers.engagement;

import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.entities.engagement.factory.EngagementScrapFactory;
import fr.athompson.scrap.enums.EngagementType;
import fr.athompson.scrap.enums.SexeCompetitionType;
import fr.athompson.scrap.scrapers.Scraper;
import fr.athompson.scrap.scrapers.competition.CompetitionScraper;
import fr.athompson.scrap.scrapers.utils.ScrapUtils;
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
public class EngagementScraper extends Scraper<List<EngagementScrap>> {

    CompetitionScraper competitionScraper;

    public EngagementScraper(@Value("${ffbb.url.engagement}") String uri, ChromeDriver driver, CompetitionScraper competitionScraper) {
        super(uri, driver);
        this.competitionScraper = competitionScraper;
    }

    public List<EngagementScrap> scrap(Document doc) throws Exception {
        var engagements = new ArrayList<EngagementScrap>();
        Elements htmlEngagements = doc.select("table.liste");
        for (Element htmlEngagement : htmlEngagements) {
            log.info(htmlEngagement.getAllElements().size());
            if (htmlEngagement.getAllElements().size() > 1) {
                var engagement = creerEngagement(htmlEngagement);
                engagements.add(engagement);
            }
        }

        return engagements;
    }

    private EngagementScrap creerEngagement(Element htmlEngagement) throws Exception {

        var htmlTypeEngagement = ScrapUtils.getFirstElementText(htmlEngagement.getElementsByClass("titre-bloc").first(), "td");

        //Parcourir l'engagement et créer les compétitions adéquates
        var equipesEngages = htmlEngagement.getElementsByTag("tr").not(".titre-bloc");

        var competitionsEngagees = new HashMap<SexeCompetitionType, List<CompetitionScrap>>();

        SexeCompetitionType sexeCompetition = null;
        var competitions = new ArrayList<CompetitionScrap>();
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
                competitions.add(creerCompetition(equipeEngage, htmlTypeEngagement));
            }
        }
        competitionsEngagees.put(sexeCompetition, competitions);
        return EngagementScrapFactory.createEngagement(EngagementType.findByLibelleHtml(htmlTypeEngagement), competitionsEngagees);
    }

    private CompetitionScrap creerCompetition(Element equipeEngage, String htmlTypeEngagement) throws Exception {
        String lienCompetition = equipeEngage.select("a").attr("href");
        var params = new Parametres(lienCompetition);
        //TODO on focus sur le championnat pour le moment
        CompetitionScrap competitionScrap;
        if (EngagementType.findByLibelleHtml(htmlTypeEngagement) == EngagementType.CHAMPIONNAT) {
            competitionScrap = competitionScraper.getData(params.getIdOrganisation(), params.getIdDivision(), params.getIdPoule());
        } else
            return null;
        //TODO Ajouter niveau,divisoin,categorie,type,sexe
        return competitionScrap;
    }
}
