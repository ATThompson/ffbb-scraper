package fr.athompson.ffbbscraper.scrapers.journee;

import fr.athompson.ffbbscraper.entities.Equipe;
import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.entities.Rencontre;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class JourneeScraper extends Scraper<Journee> implements APIJourneeScraper {

    public JourneeScraper(@Value("${ffbb.url.journee}") String uri, ChromeDriver driver) {
        super(uri, driver);
    }

    //TODO: Créer un rencontre scraper et enlever la partie rencontre

    private Integer[] getScoreEquipes(String score) {
        return Arrays.stream(score.split("-")).map(String::trim).map(Integer::valueOf).toArray(Integer[]::new);
    }

    @Override
    public Journee scrap(Document doc) {
        var rencontres = new ArrayList<Rencontre>();
        try {
            var tableRowRecontre = doc.select("tr[class*=altern-2]").not("[style='display:none']");
            for (Element rencontreElement : tableRowRecontre) {
                Elements dataRencontre = rencontreElement.getAllElements();
                Integer[] scores = getScoreEquipes(dataRencontre.get(8).text());
                var rencontre = Rencontre.builder()
                        .numeroRencontre(
                                Integer.valueOf(dataRencontre.get(1).text()))
                        .date(
                                DateTimeFormatter.toLocalDateTime(
                                        dataRencontre.get(2).text(),
                                        dataRencontre.get(3).text(),
                                        DateTimeFormatter.JJ_MM_AAAA_SLASH_HH_MM))
                        .equipeDomicile(new Equipe(dataRencontre.get(5).text()))
                        .equipeVisiteur(new Equipe(dataRencontre.get(7).text()))
                        .scoreDomicile(scores[0])
                        .scoreVisiteur(scores[1])
                        .build();
                rencontres.add(rencontre);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Journee(rencontres);
    }
}
