package fr.athompson.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class MatchCompetition {
    Competition competition;
    Rencontre rencontre;
}
