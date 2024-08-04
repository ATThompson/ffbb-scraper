package fr.athompson.domain.entities;

import fr.athompson.domain.entities.classement.Classement;
import fr.athompson.domain.enums.CategorieType;
import fr.athompson.domain.enums.DivisionType;
import fr.athompson.domain.enums.NiveauCompetitionType;
import fr.athompson.domain.enums.SexeCompetitionType;
import lombok.Builder;

import java.util.List;

@Builder
public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes,
                          SexeCompetitionType sexe,
                          NiveauCompetitionType niveau,
                          DivisionType division,
                          CategorieType categorie,
                          Integer nombrePoules,
                          Comite comite,
                          String idOrganisation,
                          String idDivision,
                          String idPoule) {


}
