# Extra Notes from the tutorial

## Spring Cloud 
Used for reliable and robust microservices by provide a set o design patterns.

### Spring Cloud Feign 
For Synchronous Communication in Microservices, Feign is recommended which wraps OpenFeign.  

It is a declarative REST Client. The architecture is a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.

### API Gateway
Acts as an entrypoint for the requests which are forward to the downstream microservices.
Commonly used in both distributed systems and microservices architecture.  

There are two types of Spring Cloud
&emsp;Spring Cloud MVC based on Tomcat and MVC
&emsp;Spring Cloud, which is built on top of Spring WebFlux or SpringWebMvc

<b>Features</b>
<ul>
    <li>Able to match routes on any request attribute</li>
    <li>Predicates and filter are specific to routes</li>
    <li>Circuit Breaker Integration</li>
    <li>DiscoverClient Integration</li>
    <li>Request Rate Limiting</li>
    <li>Path Rewriting</li>
</ul>

API gateway can act as an entry point
<b>Advantages</b>
<ul>
    <li>Transform the URL</li>
    <li>Handles cross-cutting concerns such as security, monitoring, rate limiting and SSL Termination</li>
</ul>
<b>Disadvantages</b>
<ul>
    <li>Need a lot of effort to maintain since it has multiple tasks</li>
    <li>Single Point of Failure</li>
</ul>

## API Testing 

[Product Website](https://www.red-gate.com/products/flyway/community)

A library that can create a Mock Rest Service which the developer can use to test the API and validate that
the Controller/Service/Data Layer produces the correct results.

### Example (Code used from src/test/java/com/example/inventoryservice/InventoryServiceApplicationTests.java

Include the following dependencies
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-contract-stub-runner</artifactId>
            <scope>test</scope>
        </dependency>   
```

<b>Example of a mock call to the Inventory Service to verify that the item (skuCode) and quantity is in stock.
```java 
    void shouldNotReadInventory() {
        var negativeResponse = RestAssured.given()
                .when()
                .get("/api/inventory?skuCode=iphone15&quantity=1000")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().as(Boolean.class);
        assertFalse(negativeResponse);
    }
```
## Database Migrations

[Product Website](https://www.red-gate.com/products/flyway/community)

<b>Purpose:</b>Automatically executes the group of database scripts that start with original and end with the latest 
database script to create the current version of the database.  

The1 SQL files must have a specific format: <b>"V"<number>__<name>.sql</b> (yes two underscores are needed.)
Example -- <b>Three SQL files found in resources/db.migration
<ol>
    <li>V1__createDatabase.sql</li>
    <li>V2__addOrderTable.sql</li>
    <li>V3__insertTable.sql</li>
</ol>

### Installation with SpringBoot.

Include the following dependencies in the pom.xml file
```xml
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-mysql</artifactId>
        </dependency>
```

When the SpringBoot application is executed, then Flyway will start and find the resources/db.migration.  In that directory,  
the SQL files will be executed in the following order: V1__createDatabase.sql, V2__addOrderTable.sql and V3

## KeyCloak

An Opensource Identity and Access Management Solution 

<b>Features</b>
<ul>
    <li>Single Sign On</li>
    <li>Identity Brokering</li>
    <li>User Federation</li>
    <li>Admin Console</li>
    <li>Standard Protocols -- OpenID Connect, OAuth2 and SAML</li>
</ul>

http://localhost:8181/ -- Brings up the administration console

<b>Steps Needs to create a User in a Realm</b>
<ol>
    <li>On the Drop-down select Create Realm to display the Create Realm Page.</li>
    <li>Provide a name in Field "Realm Names and click Create to display Welcome to spring-microservices-security-realm</li>
    <li>Click on Create and click Create Client</li>
    <li>Provide a Client Identification for general settings and for capability config select client authentication on (access through REST)</li>
    <li>Select Authentication Options: Service Account Roles/li>
    <li>click Save</li>
    <li>Click on Client Credentials to get the username and password</li>
</ol>

<b>Configure KeyCloak in the project</b>
<li>Select Realm Settings and OpenID Endpoint Configuration to get the OAUTH2 Configuration</li>
<li></li>

Realms. -- A set of users, clients, roles configured in a container and are isolated.  Users from another realm cannot 
access the clients of that realm.

Get a Token from the Authorization Server to call the API Gateway.  In Postman Client on the Authorization using the Authorization
endpoint 

id_token—Information about the user.

## Test Containers

[Product Website](https://testcontainers.com/)

Testcontainers is an open source framework for providing throwaway, lightweight instances of databases, 
message brokers, web browsers, or just about anything that can run in a Docker container.  

The documentation states the containers can replace mocks. However, after a few initial integration tests my
initial observations, the test containers were noticeable slower than mocks.

### Installation and Usage

<b> Add the Dependencies to the POM File (junit-jupiter) and Components (ex. MYSQL or Mongo containers)</b>

``` xml

        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-testcontainers</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>mysql</artifactId>
            <version>1.20.0</version>
            <scope>test</scope>
        </dependency>
```

<b>Configuration for the Test Java to use the Testcontainer</b>
```java

    // Create the Database URL String
    @ServiceConnection
    static MySQLContainer mySqlContainer = new MySQLContainer("mysql:8.3.0")
        .withUsername("root")
        .withPassword("mysql")
        .withDatabaseName("inventory_service");

        @LocalServerPort
        private Integer port;

        @BeforeEach
        public void setup() {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = port;
        }

        static {
            mySqlContainer.start();
        }
    // End create database URL String.
```

### Rest Assured

A library used to 

Example provides from the website

You can use REST Assured to validate interesting things from the response:

@Test public void
lotto_resource_returns_200_with_expected_id_and_winners() {

    when().
            get("/lotto/{id}", 5).
    then().
            statusCode(200).
            body("lotto.lottoId", equalTo(5),
                 "lotto.winners.winnerId", hasItems(23, 54));

}

### Swagger

Document the Rest API and to use SWAGGER use Spring Component that uses the OpenAPI Specification.  

The OpenAPI <b>Specification</b> A specification that describes a REST Service
Swagger is an implementation of the OpenAPI Specification 

[Product Website:](openapis.org)

<b>Installation and Configuration of swagger</b>

### Installation of Specification

<b>Add the dependencies to pom file
'''xml

    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version>
    </dependency>
    
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
        <version>2.5.0</version>
    </dependency>
'''

<b>Add properties to the Application Properties File</b>

| Environment Variable      | Description               | Value            | URL to view Documentation or JSON       |                                                                                                                                                  
|---------------------------|---------------------------|------------------|-----------------------------------------|
| springdoc.swagger-ui.path | Access Rest Documentation | /swagger-ui.html | (http://localhost:8081/swagger-ui.html) |
| springdoc.api-docs.path   | Access API as JSON        | /api-docs        | (http://localhost:8082/api-docs)        |



