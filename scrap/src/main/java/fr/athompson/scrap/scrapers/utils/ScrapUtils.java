package fr.athompson.scrap.scrapers.utils;

import org.jsoup.nodes.Element;

public class ScrapUtils {

    public static String getFirstElementText(Element element, String query) {
        return element.select(query).first().text().trim();
    }
}
