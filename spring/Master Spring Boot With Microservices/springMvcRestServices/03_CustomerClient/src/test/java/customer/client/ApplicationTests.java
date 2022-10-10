package customer.client;

import customer.client.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	CustomerClient customerClient;

	/**
	 * Demonstrate the HTTP Post by creating the Customer
	 */
	@Test
	public void createCustomer() {

		log.debug("Charles Stockman -- Creating a Customer ");

		Customer customer = Customer.builder().name("Charles Stockman").build();
		URI uri = customerClient.createNewCustomer(customer);

		System.out.println("Charles Stockman : The URI is " + uri);
		log.debug("Charles Stockman -- The URI is " + uri);
		log.debug("Charles Stockman -- End Creating a customer");

		assertNotNull(uri);
	}

}
