package fr.athompson.domain.entities.classement;


import java.util.ArrayList;
import java.util.List;

public record Classement(List<RowClassement> rowsClassement) {
    public Classement() {
        this(new ArrayList<>());
    }
}
