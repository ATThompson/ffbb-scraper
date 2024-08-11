package fr.athompson.scrap.scrapers;

import fr.athompson.scrap.scrapers.utils.CompteurAppelSingleton;
import fr.athompson.scrap.scrapers.utils.URIBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class Scraper<T> {

    final String uri;

    final ChromeDriver driver;

    @Getter
    private String[] paramsMethod;

    private Document getDocument(String uri) {
        int nbSecondesMax = 3;
        int nbSecondesMin = 0;
        Random random = new Random();
        int nbSecondes = random.ints(nbSecondesMin, nbSecondesMax)
                .findFirst()
                .getAsInt();
        try {
            Thread.sleep(nbSecondes*1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        CompteurAppelSingleton.getInstance().ajoutUnNombreAppel();
        driver.get(uri);
        return Jsoup.parse(driver.getPageSource());
    }

    public T getData(String... uriParams) {
        paramsMethod = uriParams;
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

    protected abstract T scrap(Document doc) throws Exception;

}
