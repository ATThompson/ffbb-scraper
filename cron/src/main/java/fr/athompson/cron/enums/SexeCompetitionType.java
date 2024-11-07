package fr.athompson.cron.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SexeCompetitionType {
    FEMININ("feminin"),
    MASCULIN("mascul"),
    MIXTE("mixte");

    final String libelleHtml;

    SexeCompetitionType(String libelleHtml) {
        this.libelleHtml = libelleHtml;
    }


    public static SexeCompetitionType findByLibelleHtml(final String libelleHtml) {
        return Arrays.stream(values())
                .filter(value -> libelleHtml.toLowerCase().contains(value.getLibelleHtml()))
                .findFirst()
                .orElse(null);
    }
}
