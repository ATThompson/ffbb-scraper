package fr.athompson.scrap.entities.classement;

import java.util.ArrayList;
import java.util.List;

public record Classement(List<RowClassement> rows) {
    public Classement() {
        this(new ArrayList<>());
    }
}
