package hu.smith.software.mockserver.demo;

import org.mockserver.client.MockServerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootApplication
public class MockServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockServerDemoApplication.class, args);
    }

}
