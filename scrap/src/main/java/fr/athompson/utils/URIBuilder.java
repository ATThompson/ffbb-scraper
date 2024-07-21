package fr.athompson.utils;

import java.text.MessageFormat;

public class URIBuilder {

    public static String build(String uri, String... pathVariables) {
        return MessageFormat.format(uri, pathVariables);
    }
}
