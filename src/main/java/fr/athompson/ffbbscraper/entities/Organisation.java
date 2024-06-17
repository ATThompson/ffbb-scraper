package fr.athompson.ffbbscraper.entities;

import fr.athompson.ffbbscraper.entities.engagement.Engagement;

import java.util.List;

public record Organisation (List<Engagement> engagements){}
