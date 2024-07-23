package fr.athompson.scrap.entities.classement;

import java.util.ArrayList;
import java.util.List;

public record ClassementScrap(List<RowClassement> rows) {
    public ClassementScrap() {
        this(new ArrayList<>());
    }
}
