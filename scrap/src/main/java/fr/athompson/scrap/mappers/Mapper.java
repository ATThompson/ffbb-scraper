package fr.athompson.scrap.mappers;

import fr.athompson.scrap.scrapers.Scraper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Mapper<S,D> {

    Scraper<S> scraper;

    public D getDataAndMap(String ...uriParams){
        S entityS = scraper.getData(uriParams);
        return map(entityS);
    }

    abstract D map(S s);
}
