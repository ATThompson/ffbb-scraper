package fr.athompson.ffbbscraper.utils;

import java.text.MessageFormat;
import java.util.List;

public class URIBuilder {

    private static final String DOT_HTML = ".html";


    public static String build(String uri,String ...pathVariables){
        return MessageFormat.format(uri,pathVariables);
    }
}
