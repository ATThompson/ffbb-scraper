package fr.athompson.ffbbscraper.scrapers.page;

import fr.athompson.ffbbscraper.scrapers.Scraper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PageScraper extends Scraper<Integer> implements APIPageScaper {

    public PageScraper(@Value("${ffbb.url.page}") String uri, ChromeDriver driver) {
        super(uri, driver);
    }

    @Override
    public Integer scrap(Document doc) {
        try {
            Elements tds = doc.select("td .tabloJournee-no-altern");
            return tds.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
