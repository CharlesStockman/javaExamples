package se.magnus.api.core.recommendation;


public record Recommendation (  Integer ProductId,  Integer recommendation,  String author,
                                Integer rate,       String content,         String serviceAddress) {}