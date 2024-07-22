package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.classement.Classement;

import java.util.List;

public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes) {
}
