This is a Spring Boot application that demonstrates combining [spring-security-spa] with OAuth2 security
to enable a user to login/register with your application either with an application managed login or a
third party authentication provider.

## Building

```bash
mvn package
``` 

## Configuring OAuth app in Github

When configuring a Github OAuth app for use with this demo, make sure to se the "Authorization callback URL" to

```
http://localhost:8080/login/oauth2/code/github
```

## Running

```bash
java -jar target/spring-security-spa-demo-0.0.1-SNAPSHOT.jar --github-client-id=ID --github-client-secret=SECRET
```

[spring-security-spa]: https://github.com/itzg/spring-security-spa