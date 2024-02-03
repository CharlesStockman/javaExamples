package se.magnus.api.composite.product;

import java.util.List;

public record ProductAggregate (Integer productId, String name, Integer weight,
                                List<RecommendationSummary> recommendations, List<ReviewSummary> summaries,
                                ServiceAddresses servicesAddresses) {}
