package fr.athompson.cron.entities;

import fr.athompson.cron.entities.engagement.EngagementScrap;

import java.util.List;

public record OrganisationScrap(String nom, List<EngagementScrap> engagements,String idOrganisation) {
}
