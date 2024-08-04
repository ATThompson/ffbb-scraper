package fr.athompson.scrap.scrapers.classement;

import fr.athompson.scrap.entities.EquipeScrap;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.entities.classement.RowClassementScrap;
import fr.athompson.scrap.scrapers.Scraper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class ClassementScraper extends Scraper<ClassementScrap> {

    public ClassementScraper(@Value("${ffbb.url.classement}") String uri, ChromeDriver driver) {
        super(uri, driver);
    }

    public ClassementScrap scrap(Document doc) throws Exception {
        var rowsClassement = new ArrayList<RowClassementScrap>();
        var tableRowClassement = doc.select("table.liste tr[class*=altern-2]");
        for (Element rowElement : tableRowClassement) {
            Elements dataRow = rowElement.getAllElements();
            var oneRowClassement = getOneRowClassement(dataRow);
            rowsClassement.add(oneRowClassement);
        }
        return new ClassementScrap(rowsClassement);
    }

    private RowClassementScrap getOneRowClassement(Elements dataRow) throws Exception {
        int addBonus = 0;
        if(dataRow.size() == 20){
            addBonus=1;
        }
        return RowClassementScrap.builder()
                .position(Integer.valueOf(dataRow.get(1).text()))
                .equipe(new EquipeScrap(dataRow.get(3).text(), getIdOrganisation(dataRow.get(3))))
                .points(Integer.valueOf(dataRow.get(4).text()))
                .matchJoues(Integer.valueOf(dataRow.get(5).text()))
                .matchGagnes(Integer.valueOf(dataRow.get(6).text()))
                .matchPerdus(Integer.valueOf(dataRow.get(7).text()))
                .matchNuls(Integer.valueOf(dataRow.get(8).text()))
                .matchPenalites(Integer.valueOf(dataRow.get(10+addBonus).text()))
                .matchForfaits(Integer.valueOf(dataRow.get(11+addBonus).text()))
                .pointsMarques(Integer.valueOf(dataRow.get(16+addBonus).text()))
                .pointsEncaisses(Integer.valueOf(dataRow.get(17+addBonus).text()))
                .pointsDifference(Integer.valueOf(dataRow.get(18+addBonus).text()))
                .build();
    }

    private String getIdOrganisation(Element node) throws Exception {
        String urlEquipe = node.attribute("href").getValue();
        Pattern pattern = Pattern.compile("\\.\\./equipe/(.*)\\.html(.*)");
        Matcher matcher = pattern.matcher(urlEquipe);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            throw new Exception(urlEquipe);
        }
    }

}
