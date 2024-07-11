package fr.athompson.ffbbscraper.scrapers.journee;

import fr.athompson.ffbbscraper.entities.Equipe;
import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.entities.Rencontre;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.DateTimeFormatter;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class JourneeScraper extends Scraper implements APIJourneeScraper{
    final String URI = "https://resultats.ffbb.com/championnat/rencontres/{0}.html";


    public Journee scrap(String paramJournee, int page){
        var rencontres = new ArrayList<Rencontre>();
        try {
            String pageHexa = Integer.toHexString(page);
            String uri = URIBuilder.build(URI, paramJournee + pageHexa);
            log.info(uri);
            Document doc = getDocument(uri);
            var tableRowRecontre = doc.select("tr[class*=altern-2]").not("[style='display:none']");
            for(Element rencontreElement:tableRowRecontre){
                Elements dataRencontre = rencontreElement.getAllElements();
                log.info(" 1 "+dataRencontre.get(1).text());
                log.info(" 2 "+dataRencontre.get(2).text());
                log.info(" 3 "+dataRencontre.get(3).text());
                log.info(" 5 "+dataRencontre.get(5).text());
                log.info(" 7 "+dataRencontre.get(7).text());
                log.info(" 8 "+dataRencontre.get(8).text());
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
                log.info(rencontreElement.text());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info(rencontres.toString());
        return new Journee(rencontres);
    };


    private Integer[] getScoreEquipes(String score){
        return Arrays.stream(score.split("-")).map(String::trim).map(Integer::valueOf).toArray(Integer[]::new);
    }

}
