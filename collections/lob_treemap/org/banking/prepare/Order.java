package lob.org.banking.prepare;

/**
 * Contains the number of stocks that wil be sold at a specific value
 */
public record Order(String symbol, String action, Integer quantity, Double priceToBeSoldAt) {};

