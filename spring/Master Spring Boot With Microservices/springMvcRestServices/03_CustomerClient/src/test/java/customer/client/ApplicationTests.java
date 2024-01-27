package customer.client;

import customer.client.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

		log.debug("Charles Stockman -- Creating a Customer using HTTP Post");

		Customer customer = Customer.builder().name("Charles Stockman").build();
		URI uri = customerClient.createNewCustomer(customer);
		assertNotNull(uri);

		System.out.println("Charles Stockman : The URI is " + uri);
		log.debug("Charles Stockman -- The URI is " + uri);

		log.debug("Charles Stockman -- End Creating a customer");

	}

	/**
	 * Demonstrate the HTTP Get by getting a user
	 */
	@Test
	public void getCustomer() {
		log.debug("Charles Stockman -- Getting a customerusing HTTP Verfb Get");

		Customer customer = Customer.builder().name("Charlie Stockman").build();
		URI uri = customerClient.createNewCustomer(customer);

		String uuidString = uri.toString().replace("api/v1/customer/", "");
		UUID uuid = UUID.fromString(uuidString);
		log.debug("Charles Stockman: Created a new customer The UUID is " + uuidString);

		Customer customer2 = customerClient.getCustomerById(uuid);
		log.debug("Charles Stockman : Get Customer by uuid " + customer2.toString());

		assertNotNull(customer2);
		assertNotNull(customer2.getPrimaryKey());
		assertNotNull(customer2.getName());

		log.debug("Charles Stockman -- Ending getting a customer");
	}

	/**
	 * Demonstrate a put
	 */
	@Test public void updateCustomer() {
		log.debug("Charles Stockman -- Updating a customer using the put HTTP Verb");

		Customer customer = Customer.builder().name("Charlie Stockman").build();
		URI uri = customerClient.createNewCustomer(customer);
		log.debug("Charlie Stockman: created customer " + customer);

		String uuidString = uri.toString().replace("api/v1/customer/", "");
		UUID uuid = UUID.fromString(uuidString);
		log.debug("Charles Stockman: Created a new customer The UUID is " + uuidString);

		customer = customerClient.getCustomerById(uuid);
		log.debug("Charles Stockman : The contents of customer is " + customer.toString());

		customer.setName("Charles Stockman");
		customerClient.updateNewCustomer(customer);

		assertEquals(customer.getName(), "Charles Stockman");

	}

	@Test public void deleteCustomer() {
		Customer customer = Customer.builder().name("Charlie Stockman").build();
		URI uri = customerClient.createNewCustomer(customer);
		log.debug("Charlie Stockman: created customer " + customer);

		String uuidString = uri.toString().replace("api/v1/customer/", "");
		UUID uuid = UUID.fromString(uuidString);
		log.debug("Charles Stockman: Created a new customer The UUID is " + uuidString);

		customerClient.
	}

}
