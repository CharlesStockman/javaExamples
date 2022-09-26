package guru.springframework.actuator.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpointMvcAdapter extends EndpointMvcAdapter {

    /**
     * Create an instance of CustomMvcEndpoint
     */
    @Autowired
    public CustomEndpointMvcAdapter(CustomEndpoint endpoint) {
        super(endpoint);
    }
    
    
}
