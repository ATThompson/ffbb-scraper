package fr.athompson.ffbbscraper.entities;

import fr.athompson.ffbbscraper.entities.engagement.Engagement;

import java.util.List;

public record Organisation(String nom, List<Engagement> engagements) {
}
