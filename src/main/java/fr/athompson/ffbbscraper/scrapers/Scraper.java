package fr.athompson.ffbbscraper.scrapers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.concurrent.TimeUnit;

public abstract class Scraper {

    @Autowired
    ChromeDriver driver;

    protected Document getDocument(String uri) throws Exception {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            driver.get(uri);
            return Jsoup.parse(driver.getPageSource());
        } finally {
            //driver.quit();
        }
    }
}
