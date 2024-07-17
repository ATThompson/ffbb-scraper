package fr.athompson.ffbbscraper;

import org.jsoup.nodes.Document;

public interface RequisPourScrap<T> {

    T scrap(Document doc);

}
