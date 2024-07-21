package fr.athompson.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SexeCompetitionType {
    FEMININ("Equipes féminines"),
    MASCULIN("Equipes masculines"),
    MIXTE("Equipes mixtes");

    final String libelleHtml;

    SexeCompetitionType(String libelleHtml) {
        this.libelleHtml = libelleHtml;
    }


    public static SexeCompetitionType findByLibelleHtml(final String libelleHtml) {
        return Arrays.stream(values())
                .filter(value -> value.getLibelleHtml().equals(libelleHtml))
                .findFirst()
                .orElse(null);
    }
}
