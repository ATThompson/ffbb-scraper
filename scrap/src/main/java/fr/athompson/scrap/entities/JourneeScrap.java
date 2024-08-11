package fr.athompson.scrap.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JourneeScrap {
    List<RencontreScrap> recontres;
    Integer idJournee;

    public JourneeScrap(List<RencontreScrap> recontres) {
        this.recontres = recontres;
    }
}
