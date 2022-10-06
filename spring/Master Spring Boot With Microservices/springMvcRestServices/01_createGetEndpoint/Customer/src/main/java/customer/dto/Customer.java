package customer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Creates a person that can potentially buy items
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    // Uniquely identifies a customer
    private UUID primaryKey;

    // Name of the customer
    private String name;
}
