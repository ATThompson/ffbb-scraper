package fr.athompson.scrap.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JourneeScrap {
    List<RencontreScrap> rencontres;
    Integer idJournee;

    public JourneeScrap(List<RencontreScrap> rencontres) {
        this.rencontres = rencontres;
    }
}
