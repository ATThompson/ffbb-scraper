package fr.athompson.ffbbscraper.scrapers.page;

import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class PageScraper extends Scraper implements APIPageScaper{

    final String URI = "https://resultats.ffbb.com/championnat/journees/{0}.html";

    @Override
    public Integer scrap(String param){

        String uri = URIBuilder.build(URI,param);
        log.info("scrap PageScraper "+uri);
        try {
            Document doc = getDocument(uri);
            Elements tds = doc.select("td .tabloJournee-no-altern");
            log.info("scrap PageScraper "+tds.size());
            return tds.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

}
