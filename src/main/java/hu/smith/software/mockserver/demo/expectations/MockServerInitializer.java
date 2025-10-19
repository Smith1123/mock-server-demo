package hu.smith.software.mockserver.demo.expectations;

import jakarta.annotation.PostConstruct;
import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MockServerInitializer {
    @Value("${mockserver.host}")
    private String mockserverHost;

    @PostConstruct
    public void setupExpectations() {
        ConfigurationProperties.enableCORSForAllResponses(true);
        ConfigurationProperties.corsAllowOrigin("*");
        ConfigurationProperties.corsAllowMethods("GET, OPTIONS");
        ConfigurationProperties.corsAllowHeaders("*");
        MockServerClient mockClient = new MockServerClient(mockserverHost, 1080);

        mockClient
            .when(
                HttpRequest.request()
                    .withMethod("OPTIONS")
                    .withPath("/")
            )
            .respond(
                HttpResponse.response()
                    .withStatusCode(200)
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
                        .withBody("Hello World")
                );
    }
}
