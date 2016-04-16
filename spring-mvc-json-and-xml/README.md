To run this demo, use the following command, then use cURL to test JSON and XML serialization.

```
mvn jetty:run
```

```
curl -H "Accept: application/xml" http://localhost:8080/spring-mvc-json-and-xml/comments/
curl -H "Accept: application/json" http://localhost:8080/spring-mvc-json-and-xml/comments/
```

To generate project configuration for Eclipse, use the following command, then use _File -> Import -> Existing Projects into Workspace_.

```
mvn eclipse:eclipse -DdownloadSources=true  -DdownloadJavadocs=true
```

