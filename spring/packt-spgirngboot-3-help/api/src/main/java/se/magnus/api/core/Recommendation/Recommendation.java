package se.magnus.api.core.recommendation;


public record Recommendation (  Integer ProductId,  Integer rcommendation,  String author,
                                Integer rate,       String content,         String sserviceAddress) {};