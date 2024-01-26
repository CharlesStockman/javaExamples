package customer.client;

import customer.client.dto.Customer;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;


@Component
@ConfigurationProperties(value="customer", ignoreUnknownFields = false)
@ToString
@Setter
@Slf4j
public class CustomerClient {

    // The path to the api
    private String api;

    // The computer the api is located on
    private String host;

    // The port that is connected to the api
    private String port;

    // The base URL string to allow communication with the Server API
    private String baseURL = null;

    // Connects to the server and interact with API
    public RestTemplate restTemplate;

    /**
     * Create an instance of CustomerClient and injects the RestTemplate so the environment can configure it.
     *
     * @param restTemplateBuilder Will create a restTemplate configured by the environment
     */
    public CustomerClient(RestTemplateBuilder restTemplateBuilder ) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Building the URL from configuration proeprties using lazy loading
     *
     * @return  The base URL
     */
    private String buildUrl() {
        String url =  ( baseURL == null ) ?  "http://" + host + ":" + port + api : baseURL;
        log.debug("Charles Stockman: Create the baseURL = " + url);
        return url;
    }

    /**
     * public BeerDto getBeerById(UUID uuid) { return restTemplate.getForObject(apiHost +
     * BEER_PATH_V1 + uuid.toSTring, BeerDto.class);
     */
    public Customer getCustomerById(UUID uuid) {
        Customer customer = restTemplate.getForObject(buildUrl() + "/" + uuid, Customer.class);
        log.debug(String.format("The UUID %s has data %s", uuid, customer));
        return customer;
    }

    /**
     * Contact the server to create a new customer
     */
    public URI createNewCustomer(Customer customer) {
        URI uri = restTemplate.postForLocation(buildUrl(), customer);
        log.debug("Charles Stockman: create a customer with uri " + uri);
        return uri;
    }

    /**
     * Contact the server to update a customer
     */
    public void updateNewCustomer(Customer customer) {

        log.debug("Charles Stockman Update a customer with data " + customer.toString());

        restTemplate.put(buildUrl() + "/" + customer.getPrimaryKey().toString(), customer);

        log.debug("Charles Stockman new customer with data " + customer.toString());
    }

    /**
     * Contact the server to delete a customer
     */
    public void deleteCustomer(Customer customer ) {
        log.debug("Charles Stockman delete a customer with data " + customer.toString());

        restTemplate.delete(buildUrl() + "/" + customer.getPrimaryKey().toString());
    }







}
