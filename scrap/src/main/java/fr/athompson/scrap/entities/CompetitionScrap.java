package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.enums.CategorieType;
import fr.athompson.scrap.enums.DivisionType;
import fr.athompson.scrap.enums.NiveauCompetitionType;
import fr.athompson.scrap.enums.SexeCompetitionType;
import lombok.Builder;

import java.util.List;


@Builder
public record CompetitionScrap(String nom,
                               ClassementScrap classement,
                               List<JourneeScrap> journees,
                               List<EquipeScrap> equipes,
                               SexeCompetitionType sexe,
                               NiveauCompetitionType niveau,
                               DivisionType division,
                               CategorieType categorie,
                               Integer nombrePoules,
                               String poule,
                               Boolean isEspoir,
                               ComiteScrap comite,
                               String idOrganisation,
                               String idDivision,
                               String idPoule) {
}
