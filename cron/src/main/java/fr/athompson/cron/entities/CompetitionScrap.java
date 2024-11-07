package fr.athompson.cron.entities;

import fr.athompson.cron.entities.classement.ClassementScrap;
import fr.athompson.cron.enums.CategorieType;
import fr.athompson.cron.enums.DivisionType;
import fr.athompson.cron.enums.NiveauCompetitionType;
import fr.athompson.cron.enums.SexeCompetitionType;
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
