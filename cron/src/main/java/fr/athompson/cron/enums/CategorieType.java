package fr.athompson.cron.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CategorieType {
    U9("u9"),
    U11("u11"),
    U13("u13"),
    U14("u14"),
    U15("u15"),
    U16("u16"),
    U17("u17"),
    U18("u18"),
    U19("u19"),
    U20("u20"),
    U21("u21"),
    SENIOR("senior");


    final String libelleHtml;

    CategorieType(String libelleHtml) {
        this.libelleHtml = libelleHtml;
    }

    public static CategorieType findByLibelleHtml(final String libelleHtml) {
        return Arrays.stream(values())
                .filter(value -> libelleHtml.toLowerCase().contains(value.getLibelleHtml()))
                .findFirst()
                .orElse(null);
    }
}
