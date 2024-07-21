package fr.athompson.entities;

import fr.athompson.entities.classement.Classement;

import java.util.List;

public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes) {
}
