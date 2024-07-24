package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.classement.ClassementScrap;
import lombok.Builder;

import java.util.List;


@Builder
public record CompetitionScrap(ClassementScrap classementScrap,
                               List<JourneeScrap> journees,
                               List<EquipeScrap> equipes) {
}
