package se.magnus.api.composite.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductCompositeService {

    /**
     * Sample usage: "curl $HOST:$PORT/product-composite/1"
     *
     * @param productId     Primary Key of the project
     * @return the product composite ( ex. all parts such as summary, recommendations or null
     */
    @GetMapping(value="/product-composite/{productId}", produces = "applicatin/json")
    ProductAggregate getProduct(@PathVariable Integer productId);
}
