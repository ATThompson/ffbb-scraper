package fr.athompson.domain.entities;

import fr.athompson.domain.entities.engagement.Engagement;
import lombok.Getter;

import java.util.List;

public record Organisation(String nom, List<Engagement> engagements, String idOrganisation) {
}
