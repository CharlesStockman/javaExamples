package se.magnus.api.core.product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.magnus.api.core.product.Product;


public interface ProductService {
    /**
     * Same Usages : curl $HOST:$PORT/product/1
     * where host is the machine/container/virtual machine the server is on and is a Product API Server
     * where port is the port that is connected to the product api
     */
    @GetMapping(value="/product/{productId", produces="application/json")
    Product getProduct(@PathVariable int productID );
}