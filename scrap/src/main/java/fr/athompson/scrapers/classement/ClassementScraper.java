package fr.athompson.scrapers.classement;

import fr.athompson.entities.Equipe;
import fr.athompson.entities.classement.Classement;
import fr.athompson.entities.classement.RowClassement;
import fr.athompson.scrapers.Scraper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class ClassementScraper extends Scraper<Classement> implements APIClassementScraper {

    public ClassementScraper(@Value("${ffbb.url.classement}") String uri, ChromeDriver driver) {
        super(uri, driver);
    }

    public Classement scrap(Document doc) {
        var rowsClassement = new ArrayList<RowClassement>();
        var tableRowClassement = doc.select("table.list tr[class*=altern-2]");
        for (Element rowElement : tableRowClassement) {
            Elements dataRow = rowElement.getAllElements();
            var oneRowClassement = getOneRowClassement(dataRow);
            rowsClassement.add(oneRowClassement);
        }
        return new Classement(rowsClassement);
    }

    private RowClassement getOneRowClassement(Elements dataRow) {
        return RowClassement.builder()
                .position(Integer.valueOf(dataRow.get(1).text()))
                .equipe(new Equipe(dataRow.get(3).text()))
                .points(Integer.valueOf(dataRow.get(4).text()))
                .matchJoues(Integer.valueOf(dataRow.get(5).text()))
                .matchGagnes(Integer.valueOf(dataRow.get(6).text()))
                .matchPerdus(Integer.valueOf(dataRow.get(7).text()))
                .matchNuls(Integer.valueOf(dataRow.get(8).text()))
                .matchPenalite(Integer.valueOf(dataRow.get(10).text()))
                .matchForfait(Integer.valueOf(dataRow.get(11).text()))
                .pointsMarques(Integer.valueOf(dataRow.get(16).text()))
                .pointsEncaisses(Integer.valueOf(dataRow.get(17).text()))
                .difference(Integer.valueOf(dataRow.get(18).text()))
                .build();
    }
}
