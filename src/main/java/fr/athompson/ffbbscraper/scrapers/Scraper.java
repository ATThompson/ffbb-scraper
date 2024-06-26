package fr.athompson.ffbbscraper.scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ProtocolHandshake;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Scraper {

    protected Document getDocument(String uri) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\Antoine\\Downloads\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin");
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--webdriver-loglevel=NONE"});
        Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);
        Logger.getLogger(ProtocolHandshake.class.getName()).setLevel(Level.OFF);
        WebDriver driver = new PhantomJSDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            driver.get(uri);
            return Jsoup.parse(driver.getPageSource());
        } finally {
            driver.quit();
        }
    }
}
