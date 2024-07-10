package fr.athompson.ffbbscraper.scrapers.journee;

import fr.athompson.ffbbscraper.entities.Journee;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JourneeScraper extends Scraper implements APIJourneeScraper{
    final String URI = "https://resultats.ffbb.com/championnat/rencontres/{0}.html";


    public Journee scrap(String paramJournee, int page){
        try {
            String pageHexa = Integer.toHexString(page);
            var paramJourneeWithPageHexa = new StringBuilder(paramJournee);
            paramJourneeWithPageHexa.append(pageHexa);
            String uri = URIBuilder.build(URI,paramJourneeWithPageHexa.toString());
            log.info(uri);
            Document doc = getDocument(uri);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };


}
