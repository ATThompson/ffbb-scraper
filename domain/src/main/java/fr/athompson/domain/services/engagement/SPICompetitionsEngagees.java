package fr.athompson.domain.services.engagement;

import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.engagement.Engagement;

import java.util.List;

public interface SPICompetitionsEngagees {
    List<Competition> execute(String idOrganisation);
}
