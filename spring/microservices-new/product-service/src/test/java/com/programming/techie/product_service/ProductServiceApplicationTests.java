package com.programming.techie.product_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.product_service.dto.ProductRequest;
import com.programming.techie.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/***
 * <ol SpringBootTest>
 * <li>Search the current directory and it children for @SpringBootConfiguration</li>
 * <li>Use the SpringBoot Configuration to create the application context</li>
 * </ol>
 * <ol>@Container
 *     <li>Mark Containers that should be managed by the test containers extension</li>
 * </ol>
 */

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc       // Enables and configures auto-configuration of MockMvc
class ProductServiceApplicationTests {

    /* The MongoDBContainer string contains image name and the tags associated with */
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    /* Convert a JSON into a POJO and a POJO into a JSON come frome Jackson's library */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Gets the database information from the Mongo DB database.
     *
     * @DynamicPropertValue -- Used for integration test that need to add properties with dynamic values to the
     * environment's sets of PropertySources at the time of test creation
     *
     * @param registry  A class that dynamically adds properties to the environment that have dynamically resolved variables
     *
     */
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Disabled("Ignoring since TestContainers cannot find the docker")
    //@Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("iPhone 13")
                .description("iPhone 13")
                .price(BigDecimal.valueOf(1200))
                .build();
    }

}
