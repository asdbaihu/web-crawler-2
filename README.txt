WEB CRAWLER

A Simple web crawler which takes the url to start with and the maximum depth to crawl as input parameters. The web crawler is developed using Spring Boot.

GETTING STARTED

 From the root directory of the project, fire the following command

```
 mvn clean install
```

PREREQUISITES

Java 1.8
Maven 3


RUNNING THE APPLICATION

Run the application using the following command.

```
 mvn spring-boot:run
```

The application comes on port 8090.

Swagger API docs is available on the link.

```
http://localhost:8090/web-crawler/swagger-ui.html
```

Use Swagger to run the Application or directly call the endpoint with the required parameters.

Example:
```
http://localhost:8090/web-crawler/v1/crawl?rootUrl=http://www.google.com&maxDepth=2
```

NOTES

1) The last level Pages/Node will not be processed and so the 'title' and 'nodes' elements will be null.
2) No measures taken to prevent cycling when child pages link back to the parent as this is not required.
3) If Exception occurs while processing a url, the exception is caught and the processing is allowed to continue for other urls. The url that threw error will have error message appended on its title.

AUTHOR

* **Amal Antony**



