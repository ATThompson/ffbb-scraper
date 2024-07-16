package fr.athompson.ffbbscraper.entities.classement;

import fr.athompson.ffbbscraper.entities.Equipe;

import java.util.ArrayList;
import java.util.List;

public record Classement(List<RowClassement> rows) {
    public Classement() {
        this(new ArrayList<>());
    }
}
