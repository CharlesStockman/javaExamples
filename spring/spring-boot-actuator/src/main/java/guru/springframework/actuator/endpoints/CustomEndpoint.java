package guru.springframework.actuator.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

/**
 * Create a custom endpoint for Actuator that dispaly certain property names/values from the Java Sysem Properties
 */
@Component
public class CustomEndpoint extends AbstractEndpoint<List<String>> {

    public CustomEndpoint() { 
        super("customEndpoint", false);
    }

    public List<String> invoke() {

        final List<String> list = new ArrayList<String>();

        list.add(createString("java.vendor"));
        list.add(createString("user.dir"));
        list.add(createString("java.version"));

        return list;
    }

    /**
     * A routine to create aa specific String
     * 
     * @param     property          A system property from the JDK
     * @return    A String with property and value
     */
    private String createString(final String property) {

        final StringBuffer buffer = new StringBuffer();
        buffer.append("Property: ").append(" = ").append(property).append(": ").append(System.getProperty(property)).append("\n");
        System.out.println(buffer.toString());

        return buffer.toString();
    }
}