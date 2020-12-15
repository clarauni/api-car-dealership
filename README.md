# cardealership-rest-service

This service consists of a REST API for a car dealer chain.

Required features:

- List all available cars ordered by date of sale and date of entry, according to the interest of the manager of the premises.

- See the full details of a specific car.

- Be able to update:

     o Registration, in case car is registered
     
     o Sale date, for when it is sold
     
     o The final sale price, for when it is sold
     
     o NOTE: a sold car CANNOT be modified

- To be able to cancel a car WITHOUT selling.
- To be able to register a new car.
- To be able to see the benefits of the chain itself and to be able to explain it at dealership level.

![alt text](https://github.com/clarauni/cardealership-rest-service/blob/main/data%20structure.png?raw=true)

 
 Technology used for the Backend
- Java 11 (https://openjdk.java.net/projects/jdk/11/) 
- Spring 2.0 (https://spring.io/blog/2019/10/16/spring-boot-2-2-0)  
- Database: MySql PHPMyAdmin (https://www.phpmyadmin.net/)
- Database queries with JPA (https://spring.io/guides/gs/accessing-data-jpa/)

Link for REST API testing: https://documenter.getpostman.com/view/13646949/TVmHDetJ

![alt text](https://github.com/clarauni/cardealership-rest-service/blob/main/Postman.PNG)
