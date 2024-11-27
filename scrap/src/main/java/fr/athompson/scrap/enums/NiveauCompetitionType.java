package fr.athompson.scrap.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum NiveauCompetitionType {
    DEPARTEMENTAL(List.of("departemental","pre regional","preregional","pre-region")),
    INTER_REGIONAL(List.of("inter regional","inter-regional","interregional")),
    REGIONAL(List.of("pre national","regional","prenational","pre-nat")),
    NATIONAL(List.of("national")),
    PRO(List.of());

    final List<String> possibiliteLibelleHtml;


    NiveauCompetitionType(List<String> possibiliteLibelleHtml) {
        this.possibiliteLibelleHtml = possibiliteLibelleHtml;
    }

    public static NiveauCompetitionType  findBypossibiliteLibelle(String possibiliteLibelle) {
        return Arrays.stream(values()).filter(value -> value.getPossibiliteLibelleHtml().stream().anyMatch(possibiliteLibelle::contains)).findFirst().orElse(null);
    }
}
