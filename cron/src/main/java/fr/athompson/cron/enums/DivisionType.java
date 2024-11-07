package fr.athompson.cron.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum DivisionType {
    ELITE(List.of("élite","elite")),
    DIVISION_1(List.of("division 1","pre ","inter")),
    DIVISION_2(List.of("division 2")),
    DIVISION_3(List.of("division 3")),
    DIVISION_4(List.of("division 4")),
    DIVISION_5(List.of("division 5")),
    DIVISION_6(List.of("division 6")),
    DIVISION_7(List.of("division 7")),
    DIVISION_8(List.of("division 8")),
    DIVISION_9(List.of("division 9")),
    DIVISION_10(List.of("division 10")),
    DIVISION_11(List.of("division 11")),;

    final List<String> possibiliteLibelleHtml;

    DivisionType(List<String> possibiliteLibelleHtml) {
        this.possibiliteLibelleHtml = possibiliteLibelleHtml;
    }

    public static DivisionType  findBypossibiliteLibelle(String possibiliteLibelle) {
        return Arrays.stream(values()).filter(value -> value.getPossibiliteLibelleHtml().stream().anyMatch(possibiliteLibelle::contains)).findFirst().orElse(null);
    }
}
