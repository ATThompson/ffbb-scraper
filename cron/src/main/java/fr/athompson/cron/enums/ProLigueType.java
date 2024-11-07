package fr.athompson.cron.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProLigueType {
    PRO_A("BETCLIC ELITE"),
    ESPOIRS_PRO_A("ESPOIRS ELITE"),
    PRO_B("PRO B"),
    ESPOIRS_PRO_B("ESPOIRS PRO B"),
    LF1("La Boulangere Wonderligue"),
    LF2("LIGUE FEMININE 2");

    String libelleHtml;

    ProLigueType(String libelleHtml) {
        this.libelleHtml = libelleHtml;
    }

    public static ProLigueType findByLibelleHtml(final String libelleHtml) {
        return Arrays.stream(values())
                .filter(value -> libelleHtml.toLowerCase().contains(value.getLibelleHtml().toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public boolean in (ProLigueType ... weekEnum) {
        return Arrays.asList(weekEnum).contains(this);
    }
}
