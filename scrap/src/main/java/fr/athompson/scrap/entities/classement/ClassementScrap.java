package fr.athompson.scrap.entities.classement;

import java.util.ArrayList;
import java.util.List;

public record ClassementScrap(List<RowClassementScrap> rowsClassement) {
    public ClassementScrap() {
        this(new ArrayList<>());
    }
}
