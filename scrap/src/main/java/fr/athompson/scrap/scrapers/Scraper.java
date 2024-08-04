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

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class Scraper<T> {

    final String uri;

    final ChromeDriver driver;

    @Getter
    private String[] paramsMethod;

    private Document getDocument(String uri) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
