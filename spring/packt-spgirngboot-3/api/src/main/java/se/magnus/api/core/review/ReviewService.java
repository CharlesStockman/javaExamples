package se.magnus.api.core.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {
    /**
     * Same Usages : curl $HOST:$PORT/review?productId=1
     * where host is the machine/container/virtual machine the server is on and is a Product API Server
     * where port is the port that is connected to the product api
     */
     @GetMapping(value="/review", produces="application/json")
     List<Review> getReviews(@RequestParam(value="productId", required=true) int ProductId);
}