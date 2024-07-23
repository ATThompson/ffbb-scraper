package fr.athompson.scrap.scrapers.journee;

import fr.athompson.scrap.entities.EquipeScrap;
import fr.athompson.scrap.entities.JourneeScrap;
import fr.athompson.scrap.entities.RencontreScrap;
import fr.athompson.scrap.scrapers.Scraper;
import fr.athompson.scrap.utils.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class JourneeScraper extends Scraper<JourneeScrap>{

    public JourneeScraper(@Value("${ffbb.url.journee}") String uri, ChromeDriver driver) {
        super(uri, driver);
    }

    //TODO: Créer un rencontre scraper et enlever la partie rencontre

    protected JourneeScrap scrap(Document doc) {
        var rencontres = new ArrayList<RencontreScrap>();

        var tableRowRencontre = doc.select("tr[class*=altern-2]").not("[style='display:none']");
        for (Element rencontreElement : tableRowRencontre) {
            Elements dataRencontre = rencontreElement.getAllElements();
            rencontres.add(getRencontre(dataRencontre));
        }

        return new JourneeScrap(rencontres);
    }


    private RencontreScrap getRencontre(Elements dataRencontre) {
        Integer[] scores = getScoreEquipes(dataRencontre.get(8).text());
        return RencontreScrap.builder()
                .numeroRencontre(
                        Integer.valueOf(dataRencontre.get(1).text()))
                .date(
                        DateTimeFormatter.toLocalDateTime(
                                dataRencontre.get(2).text(),
                                dataRencontre.get(3).text(),
                                DateTimeFormatter.JJ_MM_AAAA_SLASH_HH_MM))
                .equipeScrapDomicile(new EquipeScrap(dataRencontre.get(5).text()))
                .equipeScrapVisiteur(new EquipeScrap(dataRencontre.get(7).text()))
                .scoreDomicile(scores[0])
                .scoreVisiteur(scores[1])
                .build();
    }

    private Integer[] getScoreEquipes(String score) {
        var scores = score.split("-");
        if (2 > scores.length)
            return new Integer[2];
        return Arrays.stream(scores).map(String::trim).map(this::ifNullReturn0ElseScore).toArray(Integer[]::new);
    }

    private Integer ifNullReturn0ElseScore(String score) {
        return !StringUtils.hasLength(score) ? 0 : Integer.parseInt(score);
    }
}
