package fr.athompson.cron.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EngagementType {
    CHAMPIONNAT("Equipes engagées en championnat"),
    COUPE("Equipes engagées en coupe"),
    PLATEAU("Equipes engagées en plateau");

    public String libelleHtml;

    EngagementType(String libelleHtml) {
        this.libelleHtml = libelleHtml;
    }

    public static EngagementType findByLibelleHtml(final String libelleHtml) {
        return Arrays.stream(values())
                .filter(value -> value.getLibelleHtml().equals(libelleHtml))
                .findFirst()
                .orElse(null);
    }

}
