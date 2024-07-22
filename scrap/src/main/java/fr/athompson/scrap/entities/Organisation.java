package fr.athompson.scrap.entities;

import fr.athompson.scrap.entities.engagement.Engagement;

import java.util.List;

public record Organisation(String nom, List<Engagement> engagements) {
}
