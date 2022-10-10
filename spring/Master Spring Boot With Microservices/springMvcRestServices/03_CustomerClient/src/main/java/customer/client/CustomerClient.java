package customer.client;

import customer.client.dto.Customer;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


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
     * @parma restTemplateBuilder Will create a restTemplate configured by the environment
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
        String url =  ( baseURL == null ) ?  "http://" + host + ":" + port.toString() + "/" + api : baseURL;
        log.debug("Charles Stockman: Create the baseURL = " + url);
        return url;
    }

    /**
     * public BeerDto getBeerById(UUID uuid) { return restTemplate.getForObject(apiHost +
     * BEER_PATH_V1 + uuid.toSTring, BeerDto.class);
     */
    //public Customer getCustomer() {
    //    this.restTemplate.getForObject()
    //}

    /**
     * Contact the server to create a new customer
     */
    public URI createNewCustomer(Customer customer) {
        log.debug("Charles Stockman Creating new cuomster with data = " + customer.toString());


        URI uri = restTemplate.postForLocation(buildUrl(), customer);

        log.debug("Charles Stockman The new URI is " + uri);

        return uri;
    }






}
