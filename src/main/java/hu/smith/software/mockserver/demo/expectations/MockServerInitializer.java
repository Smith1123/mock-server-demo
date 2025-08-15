package hu.smith.software.mockserver.demo.expectations;

import jakarta.annotation.PostConstruct;
import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.stereotype.Component;

@Component
public class MockServerInitializer {
    @PostConstruct
    public void setupExpectations() {
        // Csatlakozás a Dockerben futó MockServer-hez
        // Itt a host:port legyen az, amit a docker compose vagy docker run során kiexpóztál
        ConfigurationProperties.enableCORSForAllResponses(true);
        ConfigurationProperties.corsAllowOrigin("*");
        ConfigurationProperties.corsAllowMethods("GET, OPTIONS");
        ConfigurationProperties.corsAllowHeaders("*");
        MockServerClient mockClient = new MockServerClient("localhost", 1080);

        mockClient
            .when(
                HttpRequest.request()
                    .withMethod("OPTIONS")
                    .withPath("/")
            )
            .respond(
                HttpResponse.response()
                    .withStatusCode(200)
//                    .withHeader("Access-Control-Allow-Origin", "*")
//                    .withHeader("Access-Control-Allow-Methods", "GET, OPTIONS")
//                    .withHeader("Access-Control-Allow-Headers", "*")
            );

        mockClient
                .when(
                    HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/")
                )
                .respond(
                    HttpResponse.response()
                        .withStatusCode(200)
//                        .withHeader("Access-Control-Allow-Origin", "*")
//                        .withHeader("Access-Control-Allow-Methods", "GET, OPTIONS")
//                        .withHeader("Access-Control-Allow-Headers", "*")
                        .withBody("Hello World")
                );
    }
}
