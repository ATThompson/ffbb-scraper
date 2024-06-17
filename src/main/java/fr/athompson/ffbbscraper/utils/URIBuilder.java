package fr.athompson.ffbbscraper.utils;

public class URIBuilder {

    private static final String DOT_HTML = ".html";

    public static String build(String uri,String pathVariable){
        return uri + pathVariable + DOT_HTML;
    }
}
