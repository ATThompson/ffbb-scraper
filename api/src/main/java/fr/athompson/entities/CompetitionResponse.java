package fr.athompson.entities;

import fr.athompson.domain.enums.CategorieType;
import fr.athompson.domain.enums.DivisionType;
import fr.athompson.domain.enums.NiveauCompetitionType;
import fr.athompson.domain.enums.SexeCompetitionType;

public record CompetitionResponse(
        String nom,
        SexeCompetitionType sexe,
        NiveauCompetitionType niveau,
        DivisionType division,
        CategorieType categorie,
        Integer nombrePoules,
        String poule,
        Boolean isEspoir,
        String identifiantChampionnat,
        String identifiantDivision,
        String identifiantPoule
) {
}
