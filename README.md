Basic application startup guide (in localhost, mvn command needed):
- execute the following command in the same directory that the pom.xml file is (~/product): mvn clean test spring-boot:run 
this command is to ensure that the tests compiled files are cleaned first, then recompiled and executed, before starting the spring boot server

OpenApi:
http://localhost:8080/api-docs

SwaggerUI:
http://localhost:8080/swagger-ui/index.html

Endpoint:
The endpoint to retrieve the products is located in the ProductController.java class, is a GET request that if successful, will return a Response Entity enveloping a Page with code 200 with all the ProductDto objects required.
This response also indicates the "totalPages" and "totalElements", so the frontal or receiving part know how many pages and elements more can obtain with the same filter values.
This endpoint can receive the following parameters:
* category (string): its not required, if this parameter is not send, the application will search for all the categories.
* sortBy (string): indicates the field in which the result will be sorted. It doesn't matter if it is in lower case or upper case, the service will standarize it to lower case. If this param is not SKU, Price, Description or Category, it will return a bad request
* ascending (boolean, default is true): indicates if the order of the result will be sorted in ascending manner if the param is true or descending manner if it is false.
* page (int, default is 0): indicates which page of the query result will be showed, starting from 0. For example, if the query could contain 20 objects, but the page size is set to 5, and the page parameter is set to 0, it will show the first 5 objects depending on the order. To retrieve the next 5 objects, the same query should be done but changing the page parameter to 1.
* size (int, default is 5): indicates the number of objects showed per page
			
Example requests:
(GET)
http://localhost:8080/api/product/catalog?category=Home %26 Kitchen&sortBy=sku&ascending=true&page=0&size=5

(GET)
http://localhost:8080/api/product/catalog?category=Electronics&sortBy=description&ascending=true&page=0&size=5

(GET)
http://localhost:8080/api/product/catalog?sortBy=price&ascending=false&page=0&size=10


Brief explanation of architectural decisions:
For this demo, I decided to make a typical Spring Boot application separated by Controller/Service/Repository annotated layers.
* The controller class: focus on receiving the external requests and calls the service to make the required tasks.
* The service class: manages the application logic: it calls the repository to get the producs, make calculations as the discounts and maps the object from entity to dto.
* The repository interface: extends the JPA repository, which facilitates the querying and connection with a database given an entity, in this case it retrieves the products in the database with a given Page objects which parametrizes the query
* Paging: given the case the user has to scroll or tap/click next while searching products on a given web front that shows the catalog, it would be very consuming to load the whole item inventory, so it is better to query by page as needed and increment the page number as the user clicks or scroll.
* Product and ProductDTO difference: it is better that the Product entity, the class that receives the data from the database would be different than the objects returned by the service, this way you can show different data as needed to the final user, for example, in this case is not needed to show the product ID, also the discount is calculated in the service so the entity doesn't have this field and it needs to be calculated and added
* ControllerAdvisor: it is used to control exceptions that a given endpoint in the controller can throw. In this case it is used to manage cases where a sortBy value could be incorrect, returning a 500 internal server error, which is better to manage as a 400 bad request indicating that the error was caused by a incorrect parameter
* Database: a H2 database was used, so it is not necessary to create an external database to use the application. This database is created in runtime, is generated based on the file schema.sql and populated by the file data.sql. Configuration is in application.properties. It is also used for integration testing.
* Tests: Integration tests were made, covers from the controller to the repository layers and uses the same H2 database mentioned before. Unit tests were made for the service layer class and the mapper class that transforms Product to ProductDTO and Product Page to ProductDTO Page since those classes covers the calculating methods of the bussiness logic.
