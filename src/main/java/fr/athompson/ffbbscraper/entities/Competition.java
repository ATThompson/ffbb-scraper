package fr.athompson.ffbbscraper.entities;

import fr.athompson.ffbbscraper.entities.classement.Classement;

import java.util.List;

public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes) { }
