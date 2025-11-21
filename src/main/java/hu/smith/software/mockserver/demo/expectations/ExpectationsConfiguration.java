package hu.smith.software.mockserver.demo.expectations;

import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExpectationsConfiguration implements CommandLineRunner {
    @Value("${mockserver.host}")
    private String mockserverHost;

    @Override
    public void run(String... args) {
        MockServerClient client = new MockServerClient(mockserverHost, 1080);
        client.when(
            HttpRequest.request().withMethod("GET").withPath("/java")
        ).respond(
            HttpResponse.response().withStatusCode(200).withHeader(
                "Access-Control-Allow-Origin", "*"
            ).withBody("Hello from Java")
        );

        System.out.println("Java expectation set for /java");

        client.when(
            HttpRequest.request().withMethod("GET").withPath("/autoComplete")
        ).respond(
            HttpResponse.response().withStatusCode(200).withHeader(
                "Access-Control-Allow-Origin", "*"
            ).withBody(JsonBody.json("{\"message\": [\"alma\", \"körte\", \"citrom\"]}"))
        );
        System.out.println("Java expectation set for /autoComplete");
    }
}
