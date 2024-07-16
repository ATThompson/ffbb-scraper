package fr.athompson.ffbbscraper.scrapers.classement;

import fr.athompson.ffbbscraper.entities.Equipe;
import fr.athompson.ffbbscraper.entities.Rencontre;
import fr.athompson.ffbbscraper.entities.classement.Classement;
import fr.athompson.ffbbscraper.entities.classement.RowClassement;
import fr.athompson.ffbbscraper.scrapers.Scraper;
import fr.athompson.ffbbscraper.utils.DateTimeFormatter;
import fr.athompson.ffbbscraper.utils.URIBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class ClassementScraper extends Scraper implements APIClassementScraper{
    final String URI = "https://resultats.ffbb.com/championnat/classements/{0}.html";


    @Override
    public Classement scrap(String paramClassement) {
        var rowsClassement = new ArrayList<RowClassement>();
        try{
            String uri = URIBuilder.build(URI, paramClassement);
            log.info(uri);
            Document doc = getDocument(uri);
            var tableRowClassement = doc.select("table.list tr[class*=altern-2]");
            for(Element rowElement:tableRowClassement) {
                Elements dataRow = rowElement.getAllElements();
                var oneRowClassement = RowClassement.builder()
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
                rowsClassement.add(oneRowClassement);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new Classement(rowsClassement);
    }
}
