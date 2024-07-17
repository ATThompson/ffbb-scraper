package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.RequisPourScrap;
import fr.athompson.ffbbscraper.utils.CompteurAppelSingleton;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public abstract class Scraper<T> implements RequisPourScrap<T> {

    final String uri;

    final ChromeDriver driver;

    private Document getDocument(String uri) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        CompteurAppelSingleton.getInstance().ajoutUnNombreAppel();
        driver.get(uri);
        return Jsoup.parse(driver.getPageSource());
    }

    public T getData(String... uriParams) {
        String uriFormatted = URIBuilder.build(uri, uriParams);
        log.info("URI : {}", uriFormatted);
        try {
            var doc = getDocument(uriFormatted);
            return scrap(doc);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
