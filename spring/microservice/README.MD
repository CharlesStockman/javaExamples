## Microservice

### Original Source Code

| Functionality          | Description                                                                                                                                                               |
|------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Services               | There are two services address and employee.  Each has a collection of get endpoints<br/>The Employee Services uses both a Web Client and Feign Client.                   |
 | Eureka                 | A server to keep all discoverable instances of each services for the microservice. Currently each service has a unique name. Each service will have an app instance name. |
| Database               | There are two databases ( employee, address ).  The code contains the information, but need to be created manually.                                                       |
 | Directory Structure    | Using a Bill of materials and all components and services are in one directory for ease of use.                                                                           |
 | Configuration Server   | The location of the properties for each service.                                                                                                                          |   