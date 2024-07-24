package fr.athompson.domain.entities;

import fr.athompson.domain.entities.classement.Classement;

import java.util.List;

public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes) {


}
