package fr.athompson.domain.services.engagement;

import fr.athompson.domain.entities.engagement.Engagement;

import java.util.List;

public interface SPICompetitionsEngagees {
    List<Engagement> execute(String idOrganisation);
}
