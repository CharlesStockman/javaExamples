package guru.springframework.actuator.health;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * A class that implements a custom Spring Boot Actuator Health Endpoint
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {

    // Used the AtomicLong so the variable was already threadsafe since it 
    // is an instance variable
    private final AtomicLong healthValue = new AtomicLong(0);

    /**
     * If the healthValue is even the creates a <code>Health.up()</code> else creates <code>Health.down()</code>
     * 
     * @return An object that returns the state of the system and additonal details such as a messsage or exception.
     */
    @Override
    public Health health() {

        final String errorMessage = "Random value states its time to fail";
        final String errorKey = "Error1";

        final long value = healthValue.addAndGet(1);
        final Health health = (value % 2 == 0) ? Health.up().build() : Health.down().withDetail(errorKey, errorMessage).build();

        return health;
    }

}