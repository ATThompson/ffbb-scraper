package fr.athompson.scrapers.engagement;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Parametres {

    private final String idOrganisation;
    private final String idDivision;
    private final String idPoule;

    public Parametres(String lien) throws Exception {
        Pattern pattern = Pattern.compile("\\.\\./\\.\\./championnat/(.*)\\.html\\?r=(.*)&d=(.*)");
        Matcher matcher = pattern.matcher(lien);
        if (matcher.matches() && matcher.groupCount() == 3) {
            this.idOrganisation = matcher.group(1);
            this.idDivision = matcher.group(2);
            this.idPoule = matcher.group(3);
        } else {
            throw new Exception(lien);
        }
    }

}
