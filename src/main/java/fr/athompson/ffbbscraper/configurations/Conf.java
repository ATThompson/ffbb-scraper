package fr.athompson.ffbbscraper.configurations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {

    @Bean
    public ChromeDriver chromeDriver() {

        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        //Ajouter car plante sur WSL sur environnement UBUNTU
        options.addArguments("no-sandbox");
        return new ChromeDriver(options);
    }
}
