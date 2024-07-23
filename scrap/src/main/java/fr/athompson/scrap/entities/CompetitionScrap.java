package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.classement.ClassementScrap;

import java.util.List;

public record CompetitionScrap(ClassementScrap classementScrap,
                               List<JourneeScrap> journeeScraps,
                               List<EquipeScrap> equipeScraps) {
}
