package se.magnus.api.core.recommendation;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {
        /**
         * Same Usages : curl $HOST:$PORT/recommendation?productId=1
         * where host is the machine/container/virtual machine the server is on and is a Product API Server
         * where port is the port that is connected to the product api
         */
         @GetMapping(value="/recommendation", produces="application/json")
         List<Recommendation> getRecommendations(@RequestParam(value="productId", required=true) int productId) ;
}