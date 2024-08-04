package fr.athompson.scrap.scrapers.engagement;

import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.engagement.EngagementScrap;
import fr.athompson.scrap.entities.engagement.factory.EngagementScrapFactory;
import fr.athompson.scrap.enums.EngagementType;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                var engagementType = getEngagementType(htmlEngagement);
                if (engagementType == EngagementType.CHAMPIONNAT) {
                    var engagement = creerEngagements(htmlEngagement,engagementType);
                    engagements.addAll(engagement);
                }
            }
        }
        return engagements;
    }

    private static EngagementType getEngagementType(Element htmlEngagement) {
        var htmlTypeEngagement = ScrapUtils.getFirstElementText(htmlEngagement.getElementsByClass("titre-bloc").first(), "td");
        var engagementType = EngagementType.findByLibelleHtml(htmlTypeEngagement);
        return engagementType;
    }

    private List<EngagementScrap> creerEngagements(Element htmlEngagement,EngagementType engagementType) throws Exception {
        //Parcourir l'engagement et créer les compétitions adéquates
        var equipesEngages = htmlEngagement.getElementsByTag("tr").not(".titre-bloc");
        var engagements = new ArrayList<EngagementScrap>();
        for (Element equipeEngage : equipesEngages) {
            if (equipeEngage.hasClass("altern-2") || equipeEngage.hasClass("no-altern-2")) {
                String poule = getPoule(equipeEngage);
                var competition = creerCompetition(equipeEngage);
                //Paroucrir la compétition regarder les équipes
                //Si on tombe sur l'équipe qui possède notre identifiant team
                //Alors on save cette team dans l'engagement
                engagements.add(
                        EngagementScrapFactory.createEngagement(
                                engagementType,
                                competition,
                                poule
                        )
                );
            }
        }
        return engagements;
    }

    private static String getPoule(Element equipeEngage) {
        String libellePourPoule = equipeEngage.select("td.gauche").first().text();

        String poule="";
        Pattern POULE_PATTERN = Pattern.compile("\\(.*\\)");
        Matcher pouleMatcher = POULE_PATTERN.matcher(libellePourPoule);
        if(pouleMatcher.find())
            poule = pouleMatcher.group().substring(1,pouleMatcher.group().length()-1);
        return poule;
    }

    private CompetitionScrap creerCompetition(Element equipeEngage) throws Exception {
        String lienCompetition = equipeEngage.select("a").attr("href");
        var params = new Parametres(lienCompetition);
        return competitionScraper.getData(params.getIdOrganisation(), params.getIdDivision(), params.getIdPoule());
    }
}
