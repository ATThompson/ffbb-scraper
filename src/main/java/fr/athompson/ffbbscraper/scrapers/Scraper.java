package fr.athompson.ffbbscraper.scrapers;

import fr.athompson.ffbbscraper.utils.CompteurAppelSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class Scraper {

    int nbAppel = 0;

    @Autowired
    ChromeDriver driver;

    protected Document getDocument(String uri) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            CompteurAppelSingleton.getInstance().ajoutUnNombreAppel();
            driver.get(uri);
            return Jsoup.parse(driver.getPageSource());
        } finally {
            //driver.quit();
        }
    }
}
