package fr.athompson.ffbbscraper.entities;

import java.util.List;

public record Competition(Classement classement,
                          List<Journee> journees,
                          List<Equipe> equipes) { }
