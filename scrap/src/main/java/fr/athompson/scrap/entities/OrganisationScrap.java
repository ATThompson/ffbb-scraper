package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.engagement.EngagementScrap;

import java.util.List;

public record OrganisationScrap(String nom, List<EngagementScrap> engagementScraps) {
}
