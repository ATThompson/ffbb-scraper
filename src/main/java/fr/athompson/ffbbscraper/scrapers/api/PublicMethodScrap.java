package fr.athompson.ffbbscraper.scrapers.api;

/**
 * @param <T> Type retourné par le getData
 */
public interface PublicMethodScrap<T> {

    /**
     * @param uriParams nécessaire pour build l'uri
     * @return {@link T}
     */
    T getData(String... uriParams);
}
