package fr.athompson.entities;

import fr.athompson.entities.engagement.Engagement;

import java.util.List;

public record Organisation(String nom, List<Engagement> engagements) {
}
